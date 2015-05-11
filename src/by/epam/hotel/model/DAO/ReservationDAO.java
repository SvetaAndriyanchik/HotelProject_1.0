package by.epam.hotel.model.DAO;


import by.epam.hotel.model.domain.Reservation;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO extends AbstractDAO {

    private final String INSERT_RESERVATION_QUERY = "INSERT INTO reservations (room_numb, admin_login, order_id) VALUES (?, ?, ?)";
    private final String UPDATE_ORDER_STATUS_QUERY = "UPDATE orders SET order_condition = 'checked' WHERE order_id = ?";

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


}
