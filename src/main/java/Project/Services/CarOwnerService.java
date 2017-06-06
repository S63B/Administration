package Project.Services;

import Project.DAO.CarOwnerDao;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Kevin.
 */

@Service
@Transactional
public class CarOwnerService {
    private CarOwnerDao carOwnerDao;
    private CarService carService;

    @Autowired
    public CarOwnerService(CarOwnerDao carOwnerDao, CarService carService) {
        this.carOwnerDao = carOwnerDao;
        this.carService = carService;
    }

    public List<Car> getCarsByOwner(Owner owner) {
        List<Car_Ownership> ownerships = carOwnerDao.getAllByOwner(owner);
        List<Car> cars = new ArrayList<>();

        for (Car_Ownership ownership : ownerships) {
            Car_Ownership currentCarOwner = carOwnerDao.getAllByCar(ownership.getCar()).stream().max(Comparator.comparing(Car_Ownership::getPurchaseDate)).get();

            if(currentCarOwner.getOwner().getId() == owner.getId()) {
                Car car = carService.getCarById(ownership.getCar().getId());
                if(!cars.contains(car)){
                    cars.add(car);
                }
            }
        }

        return cars;
    }

        public Car_Ownership addCarToOwner(Car car, Owner owner){
        Car_Ownership car_ownership = new Car_Ownership(car, owner, DateTime.now());
        carOwnerDao.save(car_ownership);
        return car_ownership;
    }
}
