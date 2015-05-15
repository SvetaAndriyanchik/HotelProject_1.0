package by.epam.hotel.model.domain;


public class Check {
    private int checkId;
    private String login;
    private long price;

    public Check() {
    }

    public Check(String login, long price) {
        this.login = login;
        this.price = price;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (checkId != check.checkId) return false;
        if (price != check.price) return false;
        return !(login != null ? !login.equals(check.login) : check.login != null);

    }

    @Override
    public int hashCode() {
        int result = checkId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }
}
