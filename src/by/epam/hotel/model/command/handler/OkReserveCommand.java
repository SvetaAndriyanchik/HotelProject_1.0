package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OkReserveCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        int guests = Integer.parseInt(request.getParameter("guests"));
        String departureDate = request.getParameter("departure");
        String arrivalDate = request.getParameter("arrival");
        String login = String.valueOf(session.getAttribute("login"));
        int price = Integer.parseInt(request.getParameter("room_price"));
        boolean flag = true;

        OrderLogic orderLogic = new OrderLogic();
        if(guests == 0  || departureDate.isEmpty() || arrivalDate.isEmpty() || login.isEmpty()){
            request.setAttribute("errorMessage", "Null field");
            flag = false;
        }

        if(!orderLogic.checkDateInterval(arrivalDate, departureDate)){
            request.setAttribute("errorMessage", "Wrong date interval");
            flag = false;
        }

        long orderPrice = orderLogic.calculateOrderPrice(arrivalDate, departureDate, price);
        if(!orderLogic.checkUserBalance(login, orderPrice)){
            request.setAttribute("errorMessage", "No money");
            flag = false;
        }

        if(flag) {
            if(!orderLogic.createOrder(guests, arrivalDate, departureDate, login)){
                request.setAttribute("errorMessage", "Error in ordering");
                request.setAttribute("price", 0);
            } else {
                request.setAttribute("price", orderPrice);
                request.setAttribute("errorMessage", "Order sent successfully");
            }
        }
        return ConfigurationManager.getProperty("path.page.success");

    }

}

