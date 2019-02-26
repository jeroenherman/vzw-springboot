package be.voedsaam.vzw.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               @Qualifier("JPAUserDetailService") UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/h2console").disable()
                .authorizeRequests().antMatchers("/**/favicon.ico") .permitAll()
                .and().authorizeRequests().antMatchers("/product/**").permitAll()
                .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
                .and().authorizeRequests().antMatchers("/static/css").permitAll()
                .and().authorizeRequests().antMatchers("/js").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and().authorizeRequests().antMatchers("/task/**").authenticated()
                .and().authorizeRequests().antMatchers("/destination/**").authenticated()
                .and().authorizeRequests().antMatchers("/schedule/**").authenticated()
                .and().authorizeRequests().antMatchers("/drive/**").authenticated()
                .and().authorizeRequests().antMatchers("/user/**").authenticated()
                .and().exceptionHandling().accessDeniedPage("/access_denied");

        http.headers().frameOptions().disable(); // access H2 database
    }


//    @Autowired
//    StrongPasswordEncryptor passwordEncoder;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
//    }

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


//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/", "/index/**", "/drive/**", "/schedule", "/destination").permitAll()
//
//                .and().authorizeRequests().antMatchers("/static/css/**","/js/**", "/images/**", "/**/favicon.ico").permitAll()
//                .and().formLogin().loginPage("/login").defaultSuccessUrl("/user/").permitAll();
//        http.headers().frameOptions().disable(); // access H2 database
//        http.csrf().disable(); //Disable CRSF (Cross-Site Request Forgery). By default, Spring Security will protect against CRSF attacks.
//
//    }
//}
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/", "/index/**", "/drive/**", "/schedule", "/destination").permitAll()
//                .and().authorizeRequests().antMatchers("/login","logout").permitAll()
//                .and().authorizeRequests().antMatchers("/static/css/**","/js/**", "/images/**", "/**/favicon.ico").permitAll()
//                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//                .and().logout()
//                .deleteCookies("remove")
//                .invalidateHttpSession(true)
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/logout-success")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        ;
//    }

}