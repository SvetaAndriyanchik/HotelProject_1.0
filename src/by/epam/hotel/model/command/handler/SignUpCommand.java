package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        page = ConfigurationManager.getProperty("path.page.signup");
        return page;
    }
}
