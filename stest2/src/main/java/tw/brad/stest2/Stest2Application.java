package tw.brad.stest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"tw.brad"})
public class Stest2Application {

	public static void main(String[] args) {
		SpringApplication.run(Stest2Application.class, args);
	}

}
