package Project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Nekkyou on 12-6-2017.
 */
@Service
public class AuthenticationService {
	private BCryptPasswordEncoder encoder;

	@Autowired
	public AuthenticationService(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public String getLoggedinUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public String encodeString(String text) {
		return encoder.encode(text);
	}
}
