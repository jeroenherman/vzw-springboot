package be.voedsaam.vzw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * Created by jt on 12/14/15.
 */
@Configuration
@EnableJpaRepositories("be.voedsaam.vzw.business.repository")
public class CommonBeanConfig {

    @Bean
    public BCryptPasswordEncoder strongEncryptor(){
        BCryptPasswordEncoder encryptor = new BCryptPasswordEncoder();
        return encryptor;
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

}
