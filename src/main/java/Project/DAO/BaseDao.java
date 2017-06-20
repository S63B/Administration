package Project.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Nekkyou on 9-5-2017.
 */
public class BaseDao<T> {
	private final Logger logger = LoggerFactory.getLogger(BaseDao.class);

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
		}catch (Exception ex){
			logger.error("Error while creating object", ex);
			return false;
		}
	}

	public boolean save(T object){
		try{
			em.getTransaction().begin();
			em.merge(object);
			em.getTransaction().commit();
			return true;
		}catch (Exception ex){
			logger.error("Error while saving object", ex);
			return false;
		}
	}
}