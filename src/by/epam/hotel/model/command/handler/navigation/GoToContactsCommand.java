package by.epam.hotel.model.command.handler.navigation;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.command.handler.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SvetaPC on 19.04.2015.
 */
public class GoToContactsCommand implements ActionCommand {
    /**
     * Forwards to the contacts.jsp page
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.contacts");
    }
}
