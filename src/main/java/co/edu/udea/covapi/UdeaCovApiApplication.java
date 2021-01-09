package co.edu.udea.covapi;

import com.google.cloud.firestore.Firestore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UdeaCovApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(UdeaCovApiApplication.class);

	@Autowired
	private Firestore firestore;

	public static void main(String[] args) {
		SpringApplication.run(UdeaCovApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			logger.info("{} app initialized.", firestore.getOptions().getProjectId());
		};
	}
}
