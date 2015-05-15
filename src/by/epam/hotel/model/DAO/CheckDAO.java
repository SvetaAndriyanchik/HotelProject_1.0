package by.epam.hotel.model.DAO;


import by.epam.hotel.model.domain.Check;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckDAO extends AbstractDAO{

    private final String SELECT_CHECKS_QUERY = "SELECT * FROM checks WHERE login = ? AND pay_condition = 'not-payed'";
    private final String TAKE_MONEY_QUERY = "UPDATE account SET account.money = account.money - ? WHERE account.id = (SELECT users.account FROM users WHERE users.login = ?)";
    private final String UPDATE_PAY_CONDITION = "UPDATE checks SET pay_condition = 'payed' WHERE check_id = ?";

    public ArrayList<Check> selectChecks(String login){
        ArrayList<Check> checks = new ArrayList<Check>();
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_CHECKS_QUERY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            if(resultSet.next()) {
                do{
                    Check check = new Check();
                    check.setLogin(resultSet.getString("login"));
                    check.setPrice(resultSet.getInt("price"));
                    check.setCheckId(resultSet.getInt("check_id"));
                    checks.add(check);
                    i++;
                } while (resultSet.next());
            }
        } catch (DataBaseException  e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return checks;
    }

    public boolean payMoney(String login, int price, int checkId){
        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(TAKE_MONEY_QUERY);
            statement.setInt(1, price);
            statement.setString(2, login);
            statement.executeUpdate();
            statement = connection.prepareStatement(UPDATE_PAY_CONDITION);
            statement.setInt(1, checkId);
            statement.executeUpdate();
            flag = true;
        } catch (DataBaseException  e) {
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
}
