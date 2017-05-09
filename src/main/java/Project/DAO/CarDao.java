package Project.DAO;

import com.S63B.domain.Entities.Car;

import javax.persistence.NoResultException;

/**
 * Created by Nekkyou on 9-5-2017.
 */
public class CarDao extends BaseDao<Car> {

	public Car getCar(String licensePlate){
		try {
			return em.createNamedQuery("Car.getCar", Car.class).setParameter("licensePlate", licensePlate).getSingleResult();
		}catch(NoResultException e){
			// No result is acceptable, object is null.
			return null;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
