package alexnanrick.dexcomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication(exclude={MongoAutoConfiguration.class})
public class DexcomServiceApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Dexcom Service Home...";
	}

	public static void main(String[] args) {
		SpringApplication.run(DexcomServiceApplication.class, args);
	}

}
