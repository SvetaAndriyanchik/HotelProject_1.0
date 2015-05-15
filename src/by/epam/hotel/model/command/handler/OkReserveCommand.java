package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OkReserveCommand implements ActionCommand {
    /**
     * Handler for button Ok on the reservation.jsp
     * Checks the right form input
     * If th input is valid - creates new order
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        int guests = Integer.parseInt(request.getParameter("guests"));
        String departureDate = request.getParameter("departure");
        String arrivalDate = request.getParameter("arrival");
        String login = String.valueOf(session.getAttribute("login"));

        int price = Integer.parseInt(request.getParameter("room_price"));
        boolean flag = true;

        /**
         * Empty fields check
         */
        OrderLogic orderLogic = new OrderLogic();
        if(guests == 0  || departureDate.isEmpty() || arrivalDate.isEmpty() || login.isEmpty()){
            request.setAttribute("errorMessage", "Null field");
            flag = false;
        }

        /**
         * Right date interval check
         */
        if(!orderLogic.checkDateInterval(arrivalDate, departureDate)){
            request.setAttribute("errorMessage", "Wrong date interval");
            flag = false;
        }

        /**
         * User's balance check
         */

        long orderPrice = orderLogic.calculateOrderPrice(arrivalDate, departureDate, price);
        if(!orderLogic.checkUserBalance(login, orderPrice)){
            request.setAttribute("errorMessage", "No money");
            flag = false;
        }

        /**
         * Creation of the new order
         */

        if(flag) {
            if(!orderLogic.createOrder(guests, arrivalDate, departureDate, login, price)){
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

