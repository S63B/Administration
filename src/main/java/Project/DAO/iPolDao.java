package Project.DAO;

import com.S63B.domain.Entities.Pol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kevin.
 */
@Repository
public interface iPolDao extends CrudRepository<Pol, String> {
}
