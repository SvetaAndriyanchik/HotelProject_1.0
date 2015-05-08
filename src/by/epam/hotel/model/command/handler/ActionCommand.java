package by.epam.hotel.model.command.handler;

import javax.servlet.http.HttpServletRequest;


public interface ActionCommand {
    /**
     * Common interface for all command handlers
     * @param request
     * @return
     */
    String execute(HttpServletRequest request);
}
