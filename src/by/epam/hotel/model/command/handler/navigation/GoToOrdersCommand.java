package by.epam.hotel.model.command.handler.navigation;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.command.handler.ActionCommand;
import by.epam.hotel.model.logic.CheckLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class GoToOrdersCommand implements ActionCommand {
    /**
     * Forwards to the orders.jsp page
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {
        CheckLogic checkLogic = new CheckLogic();
        HttpSession session = request.getSession();
        String login = String.valueOf(session.getAttribute("login"));

        request.setAttribute("checks", checkLogic.getChecks(login));
        return ConfigurationManager.getProperty("path.page.orders");
    }
}
