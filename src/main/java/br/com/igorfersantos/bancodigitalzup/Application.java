package br.com.igorfersantos.bancodigitalzup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@ComponentScan({"br.com.igorfersantos.bancodigitalzup.config"})
@EntityScan("br.com.igorfersantos.bancodigitalzup.model")
@EnableJpaRepositories("br.com.igorfersantos.bancodigitalzup.repository")
public class Application {

	public static final String BASE_URL = "http://localhost:8080";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
