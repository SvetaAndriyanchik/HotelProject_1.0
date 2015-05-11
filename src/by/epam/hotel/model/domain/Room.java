package by.epam.hotel.model.domain;


public class Room {

    /**
     * Description of the Room entity
     */

    private int roomNumber;
    private int guests;
    private int price;
    private String description;
    private String pictureURL;

    public Room() {
    }

    public Room(int roomNumber, int guests, int price, String description, String pictureURL) {
        this.roomNumber = roomNumber;
        this.guests = guests;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomNumber != room.roomNumber) return false;
        if (guests != room.guests) return false;
        if (price != room.price) return false;
        if (description != null ? !description.equals(room.description) : room.description != null) return false;
        return !(pictureURL != null ? !pictureURL.equals(room.pictureURL) : room.pictureURL != null);

    }

    @Override
    public int hashCode() {
        int result = roomNumber;
        result = 31 * result + guests;
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pictureURL != null ? pictureURL.hashCode() : 0);
        return result;
    }
}

