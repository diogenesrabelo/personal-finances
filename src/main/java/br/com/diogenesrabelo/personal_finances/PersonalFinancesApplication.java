package br.com.diogenesrabelo.personal_finances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PersonalFinancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinancesApplication.class, args);
	}

}
