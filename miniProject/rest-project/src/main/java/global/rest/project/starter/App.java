package global.rest.project.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("global.rest.project")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

