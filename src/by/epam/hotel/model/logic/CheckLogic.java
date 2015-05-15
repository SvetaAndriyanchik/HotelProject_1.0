package by.epam.hotel.model.logic;


import by.epam.hotel.model.DAO.CheckDAO;
import by.epam.hotel.model.domain.Check;

import java.util.ArrayList;

public class CheckLogic {

    public ArrayList<Check> getChecks(String login){
        ArrayList<Check> checks = new ArrayList<Check>();
        CheckDAO dao = new CheckDAO();
        checks = dao.selectChecks(login);
        return checks;
    }

    public boolean payCheck(String login, int price, int checkId){
        boolean flag = false;
        CheckDAO dao = new CheckDAO();
        if(dao.payMoney(login, price, checkId)){
            flag = true;
        }
        return flag;
    }
}
