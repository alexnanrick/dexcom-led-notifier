package alexnanrick.dexcomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude={MongoAutoConfiguration.class})
public class DexcomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DexcomServiceApplication.class, args);
	}

}
