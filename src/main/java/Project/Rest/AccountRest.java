package Project.Rest;

import Project.Services.AccountService;
import Project.Services.AuthenticationService;
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
	private AuthenticationService authService;

	@Autowired
	public AccountRest(AccountService service, AuthenticationService authService) {
		this.service = service;
		this.authService = authService;
	}

	@RequestMapping(value = "loggedin", method = RequestMethod.GET)
	public ResponseEntity<Account> getLoggedinAccount() {
		String loggedinUser = authService.getLoggedinUser();
		Account account = service.getAccount(loggedinUser);

		return new ResponseEntity<>(account, HttpStatus.OK);
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

	@RequestMapping(value = "/alterpassword", method = RequestMethod.POST)
	public ResponseEntity<Account> alterPassword(@RequestParam("password") String newPassword) {
		//Get logged in user
		String loggedInUser = authService.getLoggedinUser();
		Account account = service.getAccount(loggedInUser);

		//Encrypt password
		String hashedPassword = authService.encodeString(newPassword);
		account.setPassword(hashedPassword);
		service.update(account);

		return new ResponseEntity<>(account, HttpStatus.OK);
	}
}
