package Project.Rest;

import Project.Services.CarService;
import com.S63B.domain.Entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/car")
public class CarRest {

    private CarService carService;

    @Autowired
    public CarRest(CarService carService){
        this.carService = carService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> allCars = carService.getAllCars();
        HttpStatus status = HttpStatus.OK;

        if (allCars.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(allCars, status);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCarById(@PathVariable("id") int id){
        Car foundCar = carService.getCarById(id);
        HttpStatus status = HttpStatus.OK;

        if (foundCar == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(foundCar, status);
    }

}
