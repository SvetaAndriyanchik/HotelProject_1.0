package by.epam.hotel.model.DAO;


import by.epam.hotel.model.domain.Order;
import by.epam.hotel.model.domain.Reservation;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO extends AbstractDAO {

    private final String INSERT_RESERVATION_QUERY = "INSERT INTO reservations (room_numb, admin_login, order_id) VALUES (?, ?, ?)";
    private final String UPDATE_ORDER_STATUS_QUERY = "UPDATE orders SET order_condition = 'checked' WHERE order_id = ?";
    private final String INSERT_CHECK_QUERY = "INSERT INTO checks (login, price, pay_condition) VALUES (?, ?, 'not-payed')";
    private final String SELECT_CHECK_DATA_QUERY = "SELECT login, order_price FROM orders WHERE order_id = ?";

    /**
     * Inserts the object {@code Reservation} into the database
     * @param reservation
     * @return
     */
    public boolean insertReservation(Reservation reservation){
        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(INSERT_RESERVATION_QUERY);
            statement.setInt(1, reservation.getRoomNumber());
            statement.setString(2, reservation.getAdminLogin());
            statement.setInt(3, reservation.getOrderId());
            statement.executeUpdate();
            if(updateOrderStatus(reservation.getOrderId())) {
                flag = true;
            }
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
     * Changes the order status from "unchecked" to "checked"
     * @param orderId
     * @return
     */
    private boolean updateOrderStatus(int orderId){
        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(UPDATE_ORDER_STATUS_QUERY);
            statement.setInt(1, orderId);
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

    public boolean insertCheck(int orderId){

        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        String login = null;
        long price = 0;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_CHECK_DATA_QUERY);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                login = resultSet.getString("login");
                price = resultSet.getLong("order_price");
            }
            statement = connection.prepareStatement(INSERT_CHECK_QUERY);
            statement.setString(1, login);
            statement.setLong(2, price);
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
