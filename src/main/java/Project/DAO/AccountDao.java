package Project.DAO;

import com.S63B.domain.Entities.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nekkyou on 6-6-2017.
 */
public interface AccountDao extends CrudRepository<Account, Integer> {
	Account findByUsername(String username);
}
