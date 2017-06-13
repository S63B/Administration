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
	private BCryptPasswordEncoder encoder;

	@Autowired
	public AccountService(AccountDao accountDao, BCryptPasswordEncoder encoder) {
		this.accountDao = accountDao;
		this.encoder = encoder;
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

	public Account update(Account account) {
		return this.accountDao.save(account);
	}

	public Account getAccount(String username) {
		return accountDao.findByUsername(username);
	}
}
