package br.com.igorfersantos.bancodigitalzup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static final String BASE_URL = "http://localhost:8080";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
