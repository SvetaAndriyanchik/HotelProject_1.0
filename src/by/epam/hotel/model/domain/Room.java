package by.epam.hotel.model.domain;


public class Room {
    private int roomNumber;
    private int guests;
    private boolean condition;
    private int price;
    private String description;
    private String pictureURL;

    public Room() {
    }

    public Room(int roomNumber, int guests, boolean condition, int price, String description, String pictureURL) {
        this.roomNumber = roomNumber;
        this.guests = guests;
        this.condition = condition;
        this.price = price;
        this.description = description;
        this.pictureURL = pictureURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}

