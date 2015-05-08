package by.epam.hotel.model.logic;


import by.epam.hotel.model.DAO.OrderDAO;
import by.epam.hotel.model.DAO.UserDAO;
import by.epam.hotel.model.domain.Order;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class OrderLogic {

    private static Logger log = Logger.getLogger(OrderLogic.class);

    public boolean createOrder(int guests, String arrivalDate, String departureDate, String login){
        boolean flag = false;
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order(guests, arrivalDate, departureDate, login);
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
}
