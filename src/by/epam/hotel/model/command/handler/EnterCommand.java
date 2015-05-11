package by.epam.hotel.model.command.handler;

import by.epam.hotel.model.command.configuration.ConfigurationManager;
import by.epam.hotel.model.logic.OrderLogic;
import by.epam.hotel.model.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class EnterCommand implements ActionCommand {
    /**
     * Handler for the button Enter on the login.jsp page
     * Checks weather user is registered in the system
     * In case of wrong login or password writes message
     * Divides the application work into 2 branches: user's and admin's
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        /**
         * If it is admin
         * Places login into the session object
         * Forms the variables orders and old_orders on the next jsp
         * Forwards to admin.jsp page
         */
        if(ConfigurationManager.getProperty("admin.username").equals(username) &&
                ConfigurationManager.getProperty("admin.password").equals(password)){
            OrderLogic orderLogic = new OrderLogic();
            if(orderLogic.selectOrders() == null){
                request.setAttribute("errorMessage", "No new orders");
            } else{
                request.setAttribute("orders", orderLogic.selectOrders());
                request.setAttribute("old_orders", orderLogic.selectOldOrders());
            }
            page = ConfigurationManager.getProperty("path.page.admin-main");
            session.setAttribute("login", username);
            /**
             * If it is user
             * Places login into the session object
             * Forwards to main.jsp page
             */
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
