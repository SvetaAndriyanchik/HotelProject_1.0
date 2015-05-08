package by.epam.hotel.model.command.handler.navigation;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.command.handler.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class GoToAboutusCommand implements ActionCommand {
    @Override
    /**
     * Forwards to the abouts.jsp page
     */
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.aboutus");
    }
}
