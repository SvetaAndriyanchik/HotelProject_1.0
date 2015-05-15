package by.epam.hotel.model.logic;


import by.epam.hotel.model.DAO.OrderDAO;
import by.epam.hotel.model.DAO.UserDAO;
import by.epam.hotel.model.domain.Order;
import by.epam.hotel.model.domain.Room;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderLogic {

    private static Logger log = Logger.getLogger(OrderLogic.class);


    public boolean createOrder(int guests, String arrivalDate, String departureDate, String login, int tax){
        boolean flag = false;
        OrderDAO orderDAO = new OrderDAO();
        long orderPrice = calculateOrderPrice(arrivalDate, departureDate, tax);
        Order order = new Order(guests, arrivalDate, departureDate, login, orderPrice);
        if(orderDAO.insertOrder(order)){
            flag = true;
        }
        return flag;
    }

    public boolean checkDateInterval(String arrivalDate, String departureDate){
        boolean flag = false;
        DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date arrival = null;
        Date departure = null;
        try {
            arrival = df.parse(arrivalDate);
            departure = df.parse(departureDate);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        Calendar cArrival = new GregorianCalendar();
        cArrival.setTime(arrival);

        Calendar cDeparture = new GregorianCalendar();
        cDeparture.setTime(departure);

        long delta = cDeparture.getTimeInMillis() - cArrival.getTimeInMillis();
        if(delta > 0){
            flag = true;
        }
        return flag;
    }

    public boolean checkUserBalance(String login, long orderPrice){
        boolean flag = false;
        UserDAO userDAO = new UserDAO();
        if(userDAO.getBalance(login) >= orderPrice){
            flag = true;
        }
        return flag;
    }

    public long calculateOrderPrice(String arrivalDate, String departureDate, int tax){
        DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date arrival = null;
        Date departure = null;

        try {
            arrival = df.parse(arrivalDate);
            departure = df.parse(departureDate);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        Calendar cArrival = new GregorianCalendar();
        cArrival.setTime(arrival);

        Calendar cDeparture = new GregorianCalendar();
        cDeparture.setTime(departure);


        long delta = cDeparture.getTimeInMillis() - cArrival.getTimeInMillis();

        long diff = delta / (1000L*60L*60L*24L);

        long price = diff*tax;

        return price;
    }
    /**
     * Method is able to return null!!!
     * @return
     */
    public ArrayList<Order> selectOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.selectUncheckedOrders();
        return orders;
    }

    /**
     * Method is able to return null!!!
     * @return
     */
    public ArrayList<Order> selectOldOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.selectCheckedOrders();
        return orders;
    }

    /**
     * Method is able to return null!!!
     * @return
     */
    public ArrayList<Room> getAvailableRooms(Order order){
        ArrayList<Room> rooms = new ArrayList<>();
        OrderDAO dao = new OrderDAO();
        rooms = dao.selectAvailableRooms(order);
        return rooms;
    }
}
