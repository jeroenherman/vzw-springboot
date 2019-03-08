package be.voedsaam.vzw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("daoAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               @Qualifier("JPAUserDetailService") UserDetailsService userDetailsService) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/h2console").disable()
                .authorizeRequests().antMatchers("/**/favicon.ico").permitAll()
                .and().authorizeRequests().antMatchers("/product/**").permitAll()
                .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
                .and().authorizeRequests().antMatchers("/static/css").permitAll()
                .and().authorizeRequests().antMatchers("/js").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                    //uncomment to open everything
//                .and().authorizeRequests().antMatchers("/task/**").permitAll()
//                .and().authorizeRequests().antMatchers("/destination/**").permitAll()
//                .and().authorizeRequests().antMatchers("/schedule/**").permitAll()
//                .and().authorizeRequests().antMatchers("/drive/**").permitAll()
//                .and().authorizeRequests().antMatchers("/user/**").permitAll()
                // comment out to disable security
                .and().authorizeRequests().antMatchers("/task/**").hasAnyAuthority("ROLE_COORDINATOR", "ROLE_LOGISTICS")
                .and().authorizeRequests().antMatchers("/destination/**").hasAnyAuthority("ROLE_COORDINATOR", "ROLE_LOGISTICS", "ROLE_DRIVER")
                .and().authorizeRequests().antMatchers("/schedule/**").hasAnyAuthority("ROLE_COORDINATOR", "ROLE_LOGISTICS", "ROLE_VOLUNTEER", "ROLE_DRIVER")
                .and().authorizeRequests().antMatchers("/drive/**").hasAnyAuthority("ROLE_COORDINATOR", "ROLE_LOGISTICS", "ROLE_DRIVER")
                .and().authorizeRequests().antMatchers("/user/**").hasAuthority("ROLE_COORDINATOR")

                .and().exceptionHandling().accessDeniedPage("/access-denied");

        http.headers().frameOptions().disable(); // access H2 database
        http.csrf().disable(); // possible fix error 500 ???
    }


// in memory security
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth
//                .inMemoryAuthentication()
//                // .passwordEncoder(NoOpPasswordEncoder.getInstance()) // changed in Spring 5 https://github.com/spring-projects/spring-security/issues/5086
//                .passwordEncoder(passwordEncoder)
//                .withUser("admin").password("$2a$10$ZRg1OOuxbLmOJt7LMbZkyeeKNIlxvoCYg2/3CxYcaXE5oqDt/pWw2").roles("ADMIN", "USER")
//                .and().withUser("user").password("user").roles( "USER");
//    }


}