package by.epam.hotel.model.DAO;


import by.epam.hotel.model.domain.User;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {

    private static Logger log = Logger.getLogger(UserDAO.class);
    private final String INSERT_USER_QUERY = "INSERT INTO users VALUES (?, md5(?), ?, ?, ?)";
    private final String SEARCH_QUERY = "SELECT * FROM users WHERE (login, password) = (?, md5(?))";
    private final String SELECT_USER_MONEY = "SELECT account.money FROM account WHERE account.id = (SELECT users.account FROM users WHERE users.login = ?)";

    /**
     * Inserts new user to the database
     * @param user
     * @return
     */
    public boolean insertUser(User user){
        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(INSERT_USER_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAccount());
            statement.setString(5, user.getRole());
            statement.executeUpdate();
            flag = true;
        } catch (DataBaseException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return flag;
    }

    /**
     * Searches for the certain user in the database
     * @param username
     * @param password
     * @return true if user exists in database
     */

    public boolean findUser(String username, String password){
        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SEARCH_QUERY);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                flag = true;
                while (resultSet.next()){
                    log.debug(resultSet.getString("login"));
                    log.debug(resultSet.getString("password"));
                }
            }

            log.debug(flag);
        } catch (DataBaseException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return flag;
    }

    public int getBalance(String login){
        int balance = 0;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_USER_MONEY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                balance = resultSet.getInt("money");
            }
            log.debug(balance);
        } catch (DataBaseException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return balance;
    }
}
