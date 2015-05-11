package by.epam.hotel.model.DAO;

import by.epam.hotel.model.domain.Room;
import by.epam.hotel.model.exception.DataBaseException;
import by.epam.hotel.model.pool.ConnectionWrapper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAO extends AbstractDAO {

    private static Logger log = Logger.getLogger(RoomDAO.class);
    private final String SELECT_ROOM_TYPES = "SELECT * FROM room_type";
    private final String SELECT_ROOMS_QUERY = "SELECT rooms.room_number, rooms.guests_number," +
            " rooms.condition, room_type.price FROM rooms JOIN room_type ON rooms.guests_number = room_type.guests_number";

    /**
     * Selects rooms and their prices from database
     * @return
     */
    public ArrayList<Room> selectRooms(){
        ArrayList<Room> rooms = new ArrayList<>();
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_ROOMS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            if(resultSet.next()) {
                do{
                    Room room = new Room();
                    room.setRoomNumber(resultSet.getInt("room_number"));
                    room.setGuests(resultSet.getInt("guests_number"));
                    room.setPrice(resultSet.getInt("price"));
                    rooms.add(i, room);
                    i++;
                } while (resultSet.next());
            }

            log.debug(rooms.size());
        } catch (DataBaseException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return rooms;
    }

    /**
     * Selects available room types from the database
     * @return
     */
    public ArrayList<Room> selectRoomTypes(){
        ArrayList<Room> rooms = new ArrayList<>();
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SELECT_ROOM_TYPES);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            if(resultSet.next()) {
                do{
                    Room room = new Room();
                    room.setGuests(resultSet.getInt("guests_number"));
                    room.setPrice(resultSet.getInt("price"));
                    room.setPictureURL(resultSet.getString("picture_url"));
                    room.setDescription(resultSet.getString("description"));
                    rooms.add(i, room);
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
        return rooms;
    }
}
