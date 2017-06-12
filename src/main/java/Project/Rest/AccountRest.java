package Project.Rest;

import Project.Services.AccountService;
import com.S63B.domain.Entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nekkyou on 6-6-2017.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class AccountRest {

	private AccountService service;

	@Autowired
	public AccountRest(AccountService service) {
		this.service = service;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<Account> registerAccount(@RequestParam("username") String username,
												   @RequestParam("password") String password) {
		Account account = service.registerAccount(username, password);

		HttpStatus status = HttpStatus.OK;
		if (account == null) {
			status = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<Account>(account, status);
	}
}
