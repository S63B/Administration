package Project.DAO;

import com.S63B.domain.Entities.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nekkyou on 9-5-2017.
 */
@Repository
public interface CarDao extends CrudRepository<Car, Integer> {
    //Car findByLicensePlate(String licensePlate);
}
