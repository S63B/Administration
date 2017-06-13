package Project.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Nekkyou on 6-6-2017.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
				.antMatchers("/account/register").permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
			.httpBasic()
				.and()
			.csrf()
			.disable();
	}
}
