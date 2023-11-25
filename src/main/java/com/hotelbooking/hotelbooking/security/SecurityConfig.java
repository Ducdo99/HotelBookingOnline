package com.hotelbooking.hotelbooking.security;


import com.hotelbooking.hotelbooking.filters.GuestFilter;
import com.hotelbooking.hotelbooking.filters.LoginRequestFilter;
import com.hotelbooking.hotelbooking.filters.RegisterFilter;
import com.hotelbooking.hotelbooking.filters.UserFilter;
import com.hotelbooking.hotelbooking.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.NoSuchAlgorithmException;

@Configuration // Use this @Configuration to manually configure Spring Security
@EnableWebSecurity // Use this @EnableWebSecurity to use Spring Security for application
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityAuthenticationFilter securityAuthenticationFilter;

    @Autowired
    private RegisterFilter registerFilter;

    @Autowired
    private LoginRequestFilter loginRequestFilter;

    @Autowired
    private GuestFilter guestFilter;

    @Autowired
    private UserFilter userFilter;

    @Autowired
    private JWTService JWTService;

    @Autowired
    private JwtAuthenticationEntityPoint jwtAuthenticationEntityPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // To handle all requests that come from outside
        // To use this method when you do not use cookies, sessions

        http.cors();
        // disable the CSRF
        http.csrf().disable();
        // Do not create HttpSession, do not also use to contain data
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // To allow any request to send web application
        http.authorizeRequests().antMatchers("/**").permitAll();
        // if use anyRequest().permitAll() with .anyRequest().authenticated(), it will occur error
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntityPoint);
        // add filter
        // Filter ordering is GuestFilter -> UserFilter -> RegisterFilter -> LoginRequestFilter -> SecurityAuthenticationFilter
        http.addFilterBefore(securityAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // add a login filter which is used to validate login information before coming SecurityAuthenticationFilter filter
        http.addFilterBefore(loginRequestFilter, SecurityAuthenticationFilter.class);
        // add a registration filter which is used to validate registration information before coming LoginRequestFilter filter
        http.addFilterBefore(registerFilter, LoginRequestFilter.class);
        // add a common guest filter which is used to validate common requests of guest before coming RegisterFilter filter
        http.addFilterBefore(guestFilter, RegisterFilter.class);
        http.addFilterAt(userFilter, GuestFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(JWTService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        return new BCryptPasswordEncoder();
    }

}
