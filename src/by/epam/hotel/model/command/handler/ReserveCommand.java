package by.epam.hotel.model.command.handler;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.OrderLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ReserveCommand implements ActionCommand {

    private static Logger log = Logger.getLogger(ReserveCommand.class);

    /**
     * Handler of the button Reserve on the rooms.jsp
     * Sets values for variables {@code guests_number, room_price, pictureURL}
     * on the page reservation
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        log.debug("Reserve execute");
        log.debug(request.getParameter("guests"));
        request.setAttribute("guests_number", request.getParameter("guests"));
        request.setAttribute("room_price", request.getParameter("price"));
        request.setAttribute("pictureURL", request.getParameter("picture"));
        return ConfigurationManager.getProperty("path.page.reservation");
    }
}
