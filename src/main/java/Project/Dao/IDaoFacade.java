package Project.Dao;

/**
 * Created by arjan on 21-3-2017.
 */
public interface IDaoFacade {
     void persist(Object o);

     void remove(Object o);

     void merge(Object o);
}
