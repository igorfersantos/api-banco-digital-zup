package br.com.igorfersantos.bancodigitalzup;

import br.com.igorfersantos.bancodigitalzup.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EnableConfigurationProperties({
		FileStorageConfig.class
})
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static final String BASE_URL = "http://localhost:8080";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// cria uma senha encriptada
		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("admin123");
		System.out.println("My hash " + result);*/
	}

}
