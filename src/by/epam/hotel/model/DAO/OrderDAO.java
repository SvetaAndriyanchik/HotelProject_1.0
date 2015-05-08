package by.epam.hotel.model.DAO;


import by.epam.hotel.model.domain.Order;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;
import org.apache.log4j.Logger;

import javax.management.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO extends AbstractDAO {

    private static Logger log = Logger.getLogger(OrderDAO.class);
    private final String INSERT_ORDER_QUERY = "INSERT INTO orders (guests, arrival_date, departure_date, login) VALUES (?, ?, ?, ?)";

    public boolean insertOrder(Order order){

        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(INSERT_ORDER_QUERY);
            statement.setInt(1, order.getGuests());
            statement.setString(2, order.getArrivalDate());
            statement.setString(3, order.getDepartureDate());
            statement.setString(4, order.getLogin());
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


}
