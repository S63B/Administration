package Project.Services;

import Project.DAO.CarDao;
import Project.DAO.iPolDao;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.LicensePlate;
import com.S63B.domain.Entities.Pol;
import com.S63B.domain.Entities.Tracker;
import com.S63B.domain.Enums;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Kevin.
 */
@Service
@Transactional
public class CarService {
    private CarDao carDao;

    @Autowired
    public CarService(CarDao carDao, iPolDao iPolDao){
        this.carDao = carDao;
        iPolDao.save(new Pol(null, 1,1,1));
    }


    public List<Car> getAllCars(){
        Car nieuwCar = createCar();

        carDao.save(nieuwCar);

        List<Car> allCars = Lists.newArrayList(carDao.findAll());

        System.out.println("COUNT: "+allCars.size());
        return allCars;
    }

    public Car getCarById(int licensePlate){
       return carDao.findOne(licensePlate);
    }

    public Car createCar(){
        LicensePlate plate = new LicensePlate("TESTDING", new DateTime());
        Tracker tracker = new Tracker(null, null);

        Car car = new Car(plate, Enums.EnergyLabel.A);
        return car;
    }
}
