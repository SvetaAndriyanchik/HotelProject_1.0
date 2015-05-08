package by.epam.hotel.model.command.factory;


import by.epam.hotel.model.command.handler.ActionCommand;
import by.epam.hotel.model.command.handler.CommandEnum;
import by.epam.hotel.model.command.handler.EmptyCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory-method for handling commands from jsp-s
 */
public class ActionFactory {

    static Logger log = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum =
                    CommandEnum.valueOf(action.toUpperCase());

            current = currentEnum.getCurrentCommand();
            log.debug(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.debug(e.getMessage());
        }
        return current;
    }
}
