package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.OrderLogic;
import by.epam.hotel.model.validator.Validator;

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

        Validator validator = new Validator();
        OrderLogic orderLogic = new OrderLogic();
        if(validator.orderIsValid(price, guests,arrivalDate, departureDate, login)){
            if(!orderLogic.createOrder(guests, arrivalDate, departureDate, login, price)){
                request.setAttribute("message", "Error in ordering");
                request.setAttribute("price", 0);
            } else {
                long orderPrice = orderLogic.calculateOrderPrice(arrivalDate, departureDate, price);
                request.setAttribute("price", orderPrice);
                request.setAttribute("message", "Order sent successfully");
            }
        } else{
            request.setAttribute("message", "Error in ordering");
        }
        /**
         * Empty fields check
         */
//        OrderLogic orderLogic = new OrderLogic();
//        if(guests == 0  || departureDate.isEmpty() || arrivalDate.isEmpty() || login.isEmpty()){
//            request.setAttribute("message", "Null field");
//            flag = false;
//        }
//
//        /**
//         * Right date interval check
//         */
//        if(!orderLogic.checkDateInterval(arrivalDate, departureDate)){
//            request.setAttribute("message", "Wrong date interval");
//            flag = false;
//        }
//
//        /**
//         * User's balance check
//         */
//
//        long orderPrice = orderLogic.calculateOrderPrice(arrivalDate, departureDate, price);
//        if(!orderLogic.checkUserBalance(login, orderPrice)){
//            request.setAttribute("message", "No money");
//            flag = false;
//        }
//
//        /**
//         * Creation of the new order
//         */

        /*if(flag) {
            if(!orderLogic.createOrder(guests, arrivalDate, departureDate, login, price)){
                request.setAttribute("message", "Error in ordering");
                request.setAttribute("price", 0);
            } else {
                request.setAttribute("price", orderPrice);
                request.setAttribute("message", "Order sent successfully");
            }
        }*/
        return ConfigurationManager.getProperty("path.page.main");

    }

}

