package pl.gredel.mongoAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MongoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApiApplication.class, args);

	}

}

