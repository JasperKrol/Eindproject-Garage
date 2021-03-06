package com.eqriesracingteam.garage.config;

import com.eqriesracingteam.garage.security.JwtRequestFilter;
import com.eqriesracingteam.garage.service.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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

    private DataSource  dataSource;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    WebSecurityConfiguration(@Lazy DataSource dataSource, @Lazy JwtRequestFilter jwtRequestFilter) {
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
    @Override
    protected void configure(HttpSecurity http) throws Exception {

                //JWT token authentication
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/garage/users/**").hasRole("ADMIN")
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.GET,"/api/garage/customers/**").hasAnyRole("OFFICE", "ADMIN","MONTEUR") // api/users if you only want the authorized person to get list of users. ** needed for more path
                .antMatchers(HttpMethod.POST,"/api/garage/customers/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/garage/customers/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/garage/customers/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/garage/customers/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/garage/cars/**").hasAnyRole("OFFICE", "MONTEUR", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/garage/cars/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/garage/cars/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/garage/cars/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/garage/cars/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers("/api/garage/appointments/**").hasAnyRole("OFFICE","ADMIN")
                .antMatchers("/api/garage/inventory/**").hasAnyRole("MONTEUR","ADMIN")
                .antMatchers("/api/garage/inspections/**").hasAnyRole("MONTEUR","OFFICE","ADMIN")
                .antMatchers("/api/garage/repairs/**").hasAnyRole("MONTEUR","OFFICE","ADMIN")
                .antMatchers("/api/garage/repairs_items/**").hasAnyRole("MONTEUR","ADMIN")
                .antMatchers("/api/garage/invoices/**").hasAnyRole("OFFICE", "MONTEUR", "ADMIN")
                .antMatchers("/api/garage/registration_papers/**").hasAnyRole("OFFICE", "ADMIN")
                .antMatchers("/api/garage/repairs_items/**").hasAnyRole("MONTEUR","ADMIN")
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
