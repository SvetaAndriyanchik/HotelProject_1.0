package by.epam.hotel.model.DAO;


import by.epam.hotel.model.domain.Order;
import by.epam.hotel.model.domain.Room;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OrderDAO extends AbstractDAO {

    private static Logger log = Logger.getLogger(OrderDAO.class);
    private final String INSERT_ORDER_QUERY = "INSERT INTO orders (guests, arrival_date, departure_date, login, order_condition, order_price) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_CHECKED_ORDERS_QUERY = "SELECT * FROM orders WHERE order_condition = 'checked'";
    private final String SELECT_ORDERS_QUERY = "SELECT * FROM orders WHERE order_condition = 'unchecked'";
    private final String SELECT_AVAILABLE_ROOMS_QUERY = "SELECT * FROM rooms AS available WHERE available.room_number NOT IN (\n" +
            "SELECT room_numb FROM (SELECT reservations.room_numb, orders.arrival_date, orders.departure_date ,\n" +
            " orders.guests FROM reservations JOIN orders ON reservations.order_id = orders.order_id)tb \n" +
            " WHERE (? BETWEEN tb.arrival_date AND tb.departure_date \n" +
            " OR ? BETWEEN tb.arrival_date AND tb.departure_date)) AND guests_number = ?";

    /**
     *
     * Inserts @param order into the database
     * @return true if inserted successfully
     */
    public boolean insertOrder(Order order){
        boolean flag = false;
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(INSERT_ORDER_QUERY);
            statement.setInt(1, order.getGuests());
            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            Date arrival = df.parse(order.getArrivalDate());
            statement.setTimestamp(2, new Timestamp(arrival.getTime()));
            Date departure = df.parse(order.getDepartureDate());
            statement.setTimestamp(3, new Timestamp(departure.getTime()));
            statement.setString(4, order.getLogin());
            statement.setString(5, "unchecked");
            statement.setLong(6, order.getOrderPrice());
            statement.executeUpdate();
            flag = true;
        } catch (DataBaseException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return flag;
    }

    /**
     * Selects unchecked orders from the database
     * @return
     */
    public ArrayList<Order> selectUncheckedOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_ORDERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            if(resultSet.next()) {
                do{
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setGuests(resultSet.getInt("guests"));
                    order.setArrivalDate(resultSet.getString("arrival_date"));
                    order.setDepartureDate(resultSet.getString("departure_date"));
                    order.setLogin(resultSet.getString("login"));
                    orders.add(i, order);
                    i++;
                } while (resultSet.next());
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
        return orders;
    }

    /**
     * Selects checked orders from the database
     * @return
     */
    public ArrayList<Order> selectCheckedOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_CHECKED_ORDERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            if(resultSet.next()) {
                do{
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setGuests(resultSet.getInt("guests"));
                    order.setArrivalDate(resultSet.getString("arrival_date"));
                    order.setDepartureDate(resultSet.getString("departure_date"));
                    order.setLogin(resultSet.getString("login"));
                    orders.add(i, order);
                    i++;
                } while (resultSet.next());
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
        return orders;
    }

    /**
     * Select the available rooms for the @param current order
     * Checks the reservations and adds to the ArrayList rooms that are
     * available for the defined date interval and guests quantity
     * @return
     */
    public ArrayList<Room> selectAvailableRooms(Order order){
        ArrayList<Room> rooms = new ArrayList<>();
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_AVAILABLE_ROOMS_QUERY);
            int i = 0;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date arrival = df.parse(order.getArrivalDate());
            Date departure = df.parse(order.getDepartureDate());
            statement.setTimestamp(1, new Timestamp(arrival.getTime()));
            statement.setTimestamp(2, new Timestamp(departure.getTime()));
            statement.setInt(3, order.getGuests());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                do{
                    Room room = new Room();
                    room.setRoomNumber(resultSet.getInt("room_number"));
                    rooms.add(room);
                    i++;
                } while (resultSet.next());
            }
        } catch (DataBaseException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return rooms;
    }

}
