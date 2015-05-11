package by.epam.hotel.model.DAO;


import by.epam.hotel.model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {

    /**
     * Describes the dao entities behavior
     * Abstract class for dao-classes
     * Implements the pool connection and gets the object of the pool
     * for the usage by child-classes
     */
    public static ConnectionPool pool = ConnectionPool.getInstance();
     static Logger log = Logger.getLogger(AbstractDAO.class);

    /**
     * Closes statement
     * @param statement
     */
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }
}
