package by.epam.hotel.model.command.handler;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class EnterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if(ConfigurationManager.getProperty("admin.username").equals(username) &&
                ConfigurationManager.getProperty("admin.password").equals(password)){
            page = ConfigurationManager.getProperty("path.page.admin-main");
        } else {
            session.setAttribute("login", username);
            UserLogic userLogic = new UserLogic();
            if (userLogic.checkUser(username, password)) {
                page = ConfigurationManager.getProperty("path.page.main");

            } else {
                request.setAttribute("errorMessage", "Error entering system");
                page = ConfigurationManager.getProperty("path.page.login");
            }
        }
        return page;
    }
}
