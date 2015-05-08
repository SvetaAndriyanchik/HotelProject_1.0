package by.epam.hotel.model.logic;


import by.epam.hotel.model.DAO.RoomDAO;
import by.epam.hotel.model.domain.Room;

import java.util.ArrayList;


public class RoomLogic {
    /**
     * Method is able to return null!!!
     * @return
     */
    public ArrayList<Room> getRoomTypes(){
        ArrayList<Room> rooms = new ArrayList<>();
        RoomDAO roomDAO = new RoomDAO();
        rooms = roomDAO.selectRoomTypes();
        return rooms;
    }
}
