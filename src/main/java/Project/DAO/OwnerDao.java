package Project.DAO;

import com.S63B.domain.Entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kevin.
 */
@Repository
public interface OwnerDao  extends CrudRepository<Owner, Integer> {
    Owner findByUsername(String username);
}
