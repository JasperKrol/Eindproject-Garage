package com.eqriesracingteam.garage.config;

import com.eqriesracingteam.garage.security.JwtRequestFilter;
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

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(PATCH,"/users/{^[\\w]$}/password").authenticated()
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .antMatchers("/customers/**").hasRole("USER") //Welke rol mag bij users? instellen
//                .antMatchers("/admin/**").hasAnyRole("USER", "ADMIN")//Als admin mag je bij admin en alles wat erachter komt als waar de user rol toegang toe heeft.
//                .antMatchers(HttpMethod.GET, "Car").authenticated() //Zodra je een geldige naam en ww hebt opgegeven onafhankelijk van welke rol mag je bij deze endpoint, maar alleen maar een get doen.
//                //.antMatchers(HttpMethod.GET, "Afspraak").permitAll()//Iedereen ongeacht of je geauthenticeerd bent mag hier de get afspraak opgeven.
//                .anyRequest().permitAll() //De rest mag iedereen zien.
//                //.anyRequest().denyAll()// De rest mag niemand zien.
//                .and()
//                .csrf().disable()
//                .formLogin().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                //JWT token authentication
                http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                        .antMatchers(HttpMethod.GET,"/customers").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST,"/customers/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/customers/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET,"/cars").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST,"/cars/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/cars/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
