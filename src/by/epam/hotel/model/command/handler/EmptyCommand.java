package by.epam.hotel.model.command.handler;





import by.epam.hotel.model.command.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    /**
     * In the event of an error or a
     * direct appeal to the controller
     * works diversion to the index.jsp page
     */
    public String execute(HttpServletRequest request) {
/* в случае ошибки или прямого обращения к контроллеру
* переадресация на первую страницу */
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
