package Project.Rest;

import Project.Services.OwnerService;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/owner")
public class OwnerRest {
    private OwnerService ownerService;

    @Autowired
    public OwnerRest(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> allOwners = ownerService.getAllOwners();
        HttpStatus status = HttpStatus.OK;

        if (allOwners.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(allOwners, status);
    }
}
