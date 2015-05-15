package by.epam.hotel.model.command.handler;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.domain.Reservation;
import by.epam.hotel.model.logic.ReservationLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SendAnswerCommand implements ActionCommand {
    /**
     * Handler for the button Ok on the order.jsp page
     * Forms the object {@code Reservation} and creates the new reservation
     * Forwards to the page success.jsp
     */
    private static Logger log = Logger.getLogger(SendAnswerCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Reservation reservation = new Reservation(Integer.parseInt(request.getParameter("room")),
                String.valueOf(session.getAttribute("login")),
                Integer.parseInt(request.getParameter("orderId")));
        ReservationLogic reservationLogic = new ReservationLogic();
        if(reservationLogic.createReservation(reservation)){
            reservationLogic.addCheck(reservation.getOrderId());
            page = ConfigurationManager.getProperty("path.page.letter");
        }
        return page;
    }
}
