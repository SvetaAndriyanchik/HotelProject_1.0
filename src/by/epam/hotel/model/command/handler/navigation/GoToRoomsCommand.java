package by.epam.hotel.model.command.handler.navigation;


import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.command.handler.ActionCommand;
import by.epam.hotel.model.domain.Room;
import by.epam.hotel.model.logic.RoomLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GoToRoomsCommand implements ActionCommand{
    @Override
    /**
     * Gets the list of rooms from database
     * Writes list into attribute rooms
     * Forwards to the rooms.jsp page
     */
    public String execute(HttpServletRequest request) {
        String page = null;
        RoomLogic roomLogic = new RoomLogic();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms = roomLogic.getRoomTypes();
        if(rooms != null){
            request.setAttribute("rooms", rooms);
        }
        return ConfigurationManager.getProperty("path.page.rooms");
    }
}
