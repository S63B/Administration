package Project.Services;

import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Kevin.
 */
@Service
@Transactional
public class AdministrationService  {
    private CarOwnerService carOwnerService;
    private CarService carService;
    private OwnerService ownerService;

    @Autowired
    public AdministrationService(CarOwnerService carOwnerService, CarService carService, OwnerService ownerService ){
        this.carOwnerService = carOwnerService;
        this.carService = carService;
        this.ownerService = ownerService;
    }

    public Owner addCarToOwner(int ownerId, int carId){
        Car foundCar = carService.getCarById(carId);
        Owner owner = ownerService.getOwner(ownerId);

        if(foundCar != null && owner != null){
            Car_Ownership ownership = carOwnerService.addCarToOwner(foundCar, owner);
            owner = ownerService.addOwnership(owner, ownership);
        }

        return owner;
    }
}
