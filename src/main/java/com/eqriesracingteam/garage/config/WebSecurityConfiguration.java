package com.eqriesracingteam.garage.config;

import com.eqriesracingteam.garage.security.JwtRequestFilter;
import com.eqriesracingteam.garage.service.UserAuthenticateService;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    WebSecurityConfiguration(DataSource dataSource, JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // Authentication

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

    }

    // Encrypt the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
    // TODO: 20-12-2021 ROLLEN EN REQUESTS INREGELEN NAAR ENDPOINTS

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                //JWT token authentication
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/garage/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/garage/customers/**").hasAnyRole("USER", "ADMIN") // api/users if you only want the authorized person to get list of users. ** needed for more path
                .antMatchers(HttpMethod.POST,"/api/garage/customers/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/garage/customers/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/garage/customers/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/garage/customers/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/garage/cars/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/garage/cars/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/garage/cars/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/garage/cars/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/garage/cars/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/authenticate").permitAll()
                //to set up, all acces for developing
                .antMatchers("/api/garage/appointments/**").permitAll()
                .antMatchers("/api/garage/inventory/**").permitAll()
                .antMatchers("/api/garage/inspections/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
