package com.eqriesracingteam.garage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.PATCH;

// TODO: 14-12-2021 ROLLEN EN REQUESTS INREGELEN NAAR ENDPOINTS

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;


    @Autowired
    WebSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // you can select your own strength in encoder parameter
        return new BCryptPasswordEncoder();
    }

    // Authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //user + authority uit memory
           /* auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
           */

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }


    //Secure endpoints with HTTP authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(PATCH,"/users/{^[\\w]$}/password").authenticated()//hasRole alleen voor 1 rol
                .antMatchers("/api/garage/users/**").hasRole("ADMIN")
                .antMatchers("/api/garage/cars/**").hasRole("USER")
                .antMatchers("/api/garage/customers/**").hasAnyRole("USER", "ADMIN")
                //authenticated voor alleen ingelogde te bezoeken
                .antMatchers(HttpMethod.GET, "hello").authenticated()
                //permit all voor iedereen ongeacht ingelogd of niet
                .antMatchers(HttpMethod.GET, "goodbye").permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
