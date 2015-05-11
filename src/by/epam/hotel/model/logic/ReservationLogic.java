package by.epam.hotel.model.logic;


import by.epam.hotel.model.DAO.ReservationDAO;
import by.epam.hotel.model.domain.Reservation;

public class ReservationLogic {

    public boolean createReservation(Reservation reservation){
        boolean flag = false;
        ReservationDAO dao = new ReservationDAO();
        if(dao.insertReservation(reservation)){
            flag = true;
        }
        return flag;
    }
}
