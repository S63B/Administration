package Project.DAO;

import com.S63B.domain.Entities.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kevin.
 */
@Repository
public interface RateDao extends CrudRepository<Rate, Integer> {
}
