package Project.Domain;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjan on 28-3-2017.
 */
public class Invoice {
    private int id;
    private User user;
    private DateTime date;
    private double totalPrice;
    private DateTime startDate;
    private DateTime endDate;
    private int paymentStatus;
    private String countryOfOrigin;


    public Invoice() {

    }

    public Invoice(int id, User user, DateTime date, double totalPrice, DateTime startDate, DateTime endDate, int paymentStatus, String countryOfOrigin) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.totalPrice = totalPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStatus = paymentStatus;
        this.countryOfOrigin = countryOfOrigin;
    }

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    //endregion
}
