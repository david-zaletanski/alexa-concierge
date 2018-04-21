package net.dzale.alexa.concierge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class AlexaConciergeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlexaConciergeApplication.class, args);
	}

}
