package Project.Rest;

import Project.Services.OwnerService;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Owner;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*")
public class OwnerRest {
    private OwnerService ownerService;

    @Autowired
    public OwnerRest(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> allOwners = ownerService.getAllOwners();
        HttpStatus status = HttpStatus.OK;

        if (allOwners.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(allOwners, status);
    }

    @RequestMapping(value = "{ownerId}/cars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getOwnersCars(@PathVariable("ownerId") int id) {
        Owner owner = ownerService.getOwner(id);
        List<Car> cars = ownerService.getOwnersCars(owner);

        
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(cars, status);
    }

    @RequestMapping(value = "{ownerId}", method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwner(@PathVariable("ownerId") int id) {
        Owner owner = ownerService.getOwner(id);
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(owner, status);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Owner> createOwner(@RequestBody RestOwner restOwner) {

        Owner createdOwner = ownerService.createOwner(createOwnerFromRestOwner(restOwner));
        HttpStatus status = HttpStatus.OK;

        if(createdOwner == null){
            status = HttpStatus.ALREADY_REPORTED;
        }

        return new ResponseEntity<>(createdOwner, status);
    }

    private Owner createOwnerFromRestOwner(RestOwner restOwner){
        Owner returnOwner = new Owner();
        returnOwner.setName(restOwner.getName());
        returnOwner.setAddress(restOwner.getAddress());
        returnOwner.setCanEditPrice(restOwner.isCanEditPrice());
        returnOwner.setResidence(restOwner.getResidence());
        returnOwner.setUsername(restOwner.getUsername());
        returnOwner.setUsesWebsite(restOwner.isUsesWebsite());
        returnOwner.setRole("user");
        returnOwner.setInvoices(new ArrayList<>());
        returnOwner.setOwnedCars(new ArrayList<>());

        return returnOwner;
    }
}

class RestOwner{
    String address;
    boolean canEditPrice;
    String name;
    String residence;
    String username;
    boolean usesWebsite;

    public RestOwner() {
    }

    String role;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCanEditPrice() {
        return canEditPrice;
    }

    public void setCanEditPrice(boolean canEditPrice) {
        this.canEditPrice = canEditPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUsesWebsite() {
        return usesWebsite;
    }

    public void setUsesWebsite(boolean usesWebsite) {
        this.usesWebsite = usesWebsite;
    }


}
