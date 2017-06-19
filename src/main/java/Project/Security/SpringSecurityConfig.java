package Project.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Created by Nekkyou on 6-6-2017.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CorsFilter(createCorsConfigurationSource()), ChannelProcessingFilter.class)
			.authorizeRequests()
				.antMatchers("/owner/create").permitAll()
				.antMatchers("/account/register").permitAll()
				.antMatchers("/invoice/generate").permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
			.httpBasic()
				.and()
			.csrf()
			.disable();
	}

	/**
	 * Creates a Cors configuration. The configuration allows pre-flight OPTIONS-requests that are automatically done by
	 * the browser when doing a REST- which can cause side effects (like POST, PUT, DELETE) or when sending Authorization
	 * headers in any REST-call (source: https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).
	 * Sources:
	 * http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/cors/CorsConfiguration.html
	 * https://stackoverflow.com/questions/40286549/spring-boot-security-cors
	 * https://stackoverflow.com/questions/39135012/spring-security-angular-2-cors-unable-to-redirect-facebook-signin
	 * @return A new Cors configuration.
	 */
	private CorsConfigurationSource createCorsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
