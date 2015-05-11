package by.epam.hotel.model.command.handler;

import by.epam.hotel.model.command.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class LogoutCommand implements ActionCommand {
    /**
     * Destroys the session object and forwards to the index.jsp page
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }
}
