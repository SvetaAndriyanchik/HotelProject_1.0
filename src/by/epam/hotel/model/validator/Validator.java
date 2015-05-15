package by.epam.hotel.model.validator;

import by.epam.hotel.model.logic.OrderLogic;

public class Validator {

    public boolean orderIsValid(int price, int guests, String arrival, String departure, String login){
        if(guests == 0  || departure.isEmpty() || arrival.isEmpty() || login.isEmpty() || price == 0){
            return false;
        }
        OrderLogic orderLogic = new OrderLogic();
        /**
         * Right date interval check
         */
        if(!orderLogic.checkDateInterval(arrival, departure)){
            return false;
        }

        /**
         * User's balance check
         */

        long orderPrice = orderLogic.calculateOrderPrice(arrival, departure, price);
        if(!orderLogic.checkUserBalance(login, orderPrice)){
            return false;
        }

        return true;
    }
}
