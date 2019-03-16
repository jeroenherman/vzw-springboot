package be.voedsaam.vzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class VzwSpringbootApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(VzwSpringbootApplication.class, args);
//	}
//
//}

@SpringBootApplication
public class VzwSpringbootApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VzwSpringbootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(VzwSpringbootApplication.class, args);
	}

}