package Project.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Nekkyou on 9-5-2017.
 */
public class BaseDao<T> {
	protected EntityManager em;

	public BaseDao() {
		em = Persistence.createEntityManagerFactory("HibernatePersistenceUnit").createEntityManager();
	}

	public boolean create(T object){
		try{
			em.getTransaction().begin();
			em.persist(object);
			em.getTransaction().commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(T object){
		try{
			em.getTransaction().begin();
			em.merge(object);
			em.getTransaction().commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}