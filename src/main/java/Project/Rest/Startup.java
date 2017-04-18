package Project.Rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"Project"})
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}
