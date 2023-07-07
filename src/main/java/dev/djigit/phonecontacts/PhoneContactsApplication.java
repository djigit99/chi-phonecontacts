package dev.djigit.phonecontacts;

import dev.djigit.phonecontacts.entity.PhoneContactUser;
import dev.djigit.phonecontacts.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class PhoneContactsApplication implements CommandLineRunner {

	private final UserService userService;

	public PhoneContactsApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PhoneContactsApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		PhoneContactUser user = new PhoneContactUser();
		user.setLogin("blah");
		user.setPassword("blah");

		PhoneContactUser user2 = new PhoneContactUser();
		user2.setLogin("user2");
		user2.setPassword("user2");

		userService.saveUser(user);
		userService.saveUser(user2);
	}
}
