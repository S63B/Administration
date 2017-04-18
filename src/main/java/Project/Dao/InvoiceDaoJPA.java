package Project.Dao;

import org.springframework.stereotype.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by arjan on 28-3-2017.
 */
@Stateless
@Repository
public class InvoiceDaoJPA implements IDaoFacade, IInvoiceDao {
    private EntityManager em;

    public InvoiceDaoJPA() {
        em = Persistence.createEntityManagerFactory("HibernatePersistenceUnit").createEntityManager();
    }

    @Override
    public void persist(Object o) {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Object o) {
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();
    }

    @Override
    public void merge(Object o) {
        em.getTransaction().begin();
        em.merge(o);
        em.getTransaction().commit();
    }

    @Override
    public void test() {

    }
}
