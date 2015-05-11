package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.domain.Order;
import by.epam.hotel.model.domain.Room;
import by.epam.hotel.model.logic.OrderLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


public class CheckOrderCommand implements ActionCommand {

    /**
     *
     */
    private static Logger log = Logger.getLogger(CheckOrderCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int guests = Integer.parseInt(request.getParameter("guests"));
        String arrivalDate = request.getParameter("arrival");
        String departureDate = request.getParameter("departure");
        String login = request.getParameter("login");
        Order order = new Order(guests, arrivalDate, departureDate, login);
        OrderLogic logic = new OrderLogic();
        ArrayList<Room> rooms = logic.getAvailableRooms(order);

        if(rooms != null) {
            request.setAttribute("rooms", rooms);
            request.setAttribute("orderId", request.getParameter("orderId"));
        } else {
            request.setAttribute("message", "No available rooms");
        }

        page = ConfigurationManager.getProperty("path.page.orderhandle");
        return page;

    }
}
