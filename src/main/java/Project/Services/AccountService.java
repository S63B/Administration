package Project.Services;

import Project.DAO.AccountDao;
import com.S63B.domain.Entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Nekkyou on 6-6-2017.
 */
@Service
public class AccountService {
	private AccountDao accountDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	public AccountService(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account registerAccount(String username, String password) {
		Account account = null;
		if (accountDao.findByUsername(username) == null) {

			//Encrypt password
			String hashedPassword = encoder.encode(password);

			account = new Account(username, hashedPassword);
			accountDao.save(account);
		}


		return account;
}
}
