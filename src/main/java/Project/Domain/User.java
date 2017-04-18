package Project.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjan on 28-3-2017.
 */
public class User {
    private int id;
    private String name;
    private String address;
    private String residence;
    private boolean usesWebsite;
    private String role;
    private boolean canEditPrice;
    private List<Invoice> invoices;
    private List<Car_Ownership> ownedCars;

    public User() {

    }

    public User(int id, String name, String address, String residence,
                boolean usesWebsite, String role, boolean canEditPrice)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.residence = residence;
        this.usesWebsite = usesWebsite;
        this.role = role;
        this.canEditPrice = canEditPrice;
        this.ownedCars = new ArrayList<Car_Ownership>();
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public boolean isUsesWebsite() {
        return usesWebsite;
    }

    public void setUsesWebsite(boolean usesWebsite) {
        this.usesWebsite = usesWebsite;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isCanEditPrice() {
        return canEditPrice;
    }

    public void setCanEditPrice(boolean canEditPrice) {
        this.canEditPrice = canEditPrice;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Car_Ownership> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(List<Car_Ownership> ownedCars) {
        this.ownedCars = ownedCars;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return false;
    }

    @Override
    public int hashCode() {
        int result = 31;
        return result;
    }

    private int getBoolHashcode(boolean bool) {
        return bool ? 1231 : 1237;
    }
}
