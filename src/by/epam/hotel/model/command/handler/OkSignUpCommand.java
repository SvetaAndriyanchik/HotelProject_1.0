package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OkSignUpCommand implements ActionCommand{
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_ACCOUNT = "account";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        int account = Integer.parseInt(request.getParameter(PARAM_NAME_ACCOUNT));
        HttpSession session = request.getSession();
        if(login != null && password != null && email != null && account != 0) {
            UserLogic userLogic = new UserLogic();
            if (userLogic.createUser(login, password, email, account, "user")) {
                session.setAttribute("login", login);
                page = ConfigurationManager.getProperty("path.page.main");
            }
            else {
                request.setAttribute("errorInsertMessage", "Error registration");
                page = ConfigurationManager.getProperty("path.page.signup");
            }
        }
        else {
            request.setAttribute("errorInsertMessage", "Error registration");
            page = ConfigurationManager.getProperty("path.page.signup");
        }
        return page;

    }
}
