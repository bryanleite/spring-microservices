package br.com.furb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.furb.domain.User;
import br.com.furb.service.UserService;

@SpringBootApplication
public class SpringMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesApplication.class, args);
	}
	
	public CommandLineRunner createUser(UserService userService) {
		return args -> {
			User user = new User();
			user.setLogin("admin");
			user.setPassword("admin");
			user.setEmail("admin@gmail.com");
			user.setUsername("Administrador");
			
			userService.save(user);
		};
	}
}
