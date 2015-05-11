package by.epam.hotel.model.domain;


public class Reservation {
    /**
     * Description of the Reservation entity
     */
    private int reservationId;
    private int roomNumber;
    private String adminLogin;
    private int orderId;

    public Reservation() {
    }

    public Reservation(int roomNumber, String adminLogin, int orderId) {
        this.roomNumber = roomNumber;
        this.adminLogin = adminLogin;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (reservationId != that.reservationId) return false;
        if (roomNumber != that.roomNumber) return false;
        if (orderId != that.orderId) return false;
        return !(adminLogin != null ? !adminLogin.equals(that.adminLogin) : that.adminLogin != null);

    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + roomNumber;
        result = 31 * result + (adminLogin != null ? adminLogin.hashCode() : 0);
        result = 31 * result + orderId;
        return result;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
