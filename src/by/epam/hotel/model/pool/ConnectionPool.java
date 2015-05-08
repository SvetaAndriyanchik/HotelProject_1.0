package by.epam.hotel.model.pool;



import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;

    private static AtomicBoolean poolCreated = new AtomicBoolean(false);


    private static final ReentrantLock lock = new ReentrantLock();
    private static  Logger logger = Logger.getLogger(ConnectionPool.class);

    private BlockingQueue<ConnectionWrapper> freeConnections;
    private BlockingQueue<ConnectionWrapper> workingConnections;


    private ConnectionPool(String url, String username, String password, int poolSize) {
        if (!poolCreated.get()) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            } catch (SQLException e) {
                throw new ExceptionInInitializerError("Opa...Database registry problem");
            }
            freeConnections = new ArrayBlockingQueue<ConnectionWrapper>(
                    poolSize);
            workingConnections = new ArrayBlockingQueue<ConnectionWrapper>(
                    poolSize);
            for (int i = 0; i < poolSize; i++) {
                ConnectionWrapper connection = null;
                try {
                    connection = new ConnectionWrapper(
                            DriverManager.getConnection(url, username, password));
                } catch (SQLException e) {
                    throw new ExceptionInInitializerError("Opa...Database connection problem");
                }
                freeConnections.add(connection);
            }
            logger.debug("Pool constructor");
            poolCreated.set(true);
        }
    }


    public static ConnectionPool getInstance(){
        String url = ConfigurationManager.getProperty("pool.url");
        String username = ConfigurationManager.getProperty("pool.username");
        String password = ConfigurationManager.getProperty("pool.password");
        int poolSize = 30;
        try {
            lock.lock();
            if(!poolCreated.get()) {
                instance = new ConnectionPool(url, username, password, poolSize);
            }
        } finally {
            lock.unlock();
        }
        logger.debug("getInstance()");
        return instance;
    }

    public ConnectionWrapper takeConnection() throws DataBaseException {
        ConnectionWrapper connection = null;
        try {
            lock.lock();
            connection = freeConnections.take();

            workingConnections.put(connection);

        } catch (InterruptedException e) {
            throw new DataBaseException(
                    "pool connect error " + e);
        }
        finally {
            lock.unlock();
        }
        return connection;
    }

    public void releaseConnection(ConnectionWrapper connection) {
        try {
            lock.lock();
            workingConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        finally {
            lock.unlock();
        }
    }

    private void clearConnectionQueue() {
        ConnectionWrapper connection;
        while ((connection = freeConnections.poll()) != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        while ((connection = workingConnections.poll()) != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }

    }

    public void closePool() {
        if (instance != null) {
        instance.clearConnectionQueue();
        instance = null;
        }
    }
}