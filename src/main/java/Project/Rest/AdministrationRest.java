package Project.Rest;

import Project.Services.AdministrationService;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/car")
public class AdministrationRest {
    private AdministrationService administrationService;

    @Autowired
    public AdministrationRest (AdministrationService administrationService){
        this.administrationService = administrationService;
    }

    @RequestMapping(value = "/{ownerId}/add/{carId}", method = RequestMethod.POST)
    public ResponseEntity<Owner> addCarToOwner(@PathVariable("ownerId") int ownerId, @PathVariable("carId") int carId) {
        Owner owner = administrationService.addCarToOwner(ownerId, carId);
        HttpStatus status = HttpStatus.OK;

        if (owner == null) {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(owner, status);
    }
}
