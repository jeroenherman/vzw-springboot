package be.voedsaam.vzw;

import be.voedsaam.vzw.business.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class VzwSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VzwSpringbootApplication.class, args);
	}

}

