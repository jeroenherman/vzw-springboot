package be.voedsaam.vzw.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

 //class that enables Security on methods with the @Secured("ROLE_ADMIN", "ROLE_USER"....) annotation

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration{

}
