package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        if(request.getParameter("language").equals("Rus")){
            session.setAttribute("Locale", "ru");
        }
        else {
            session.setAttribute("Locale", "en");
        }
        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
