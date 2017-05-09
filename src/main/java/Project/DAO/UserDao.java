package Project.DAO;

import com.S63B.domain.Entities.Owner;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 9-5-2017.
 */
public class UserDao extends BaseDao<Owner> {
	/**
	 * Return a user with a name. Replace this with a NamedQuery later on
	 * @param name the name of the user
	 * @return the user object with the same name as the parameter.
	 */
	public Owner getUser(String name) {
		try {
			return em.createNamedQuery("Owner.getOwner", Owner.class).setParameter("name", name).getSingleResult();
		}catch(NoResultException e){
			// No result is acceptable, object is null.
			return null;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
