package com.timife.prodatabase.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//This is the entry point of a secure api.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security
                .csrf()
                .disable()
                .authorizeHttpRequests()
                //meaning permit all api endpoints that starts with /auth path as it is
                //either a login, authentication, or register api that doesn't need one.
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated() //authenticate all other requests.
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider) //add an authentication provider,
                //needed any auth process
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //add custom jwtFilter
                //right before the username or password authentication filter.
        /**
         * By adding your JwtAuthFilter before the UsernamePasswordAuthenticationFilter,
         * you can intercept and authenticate requests based on JWT tokens and then allow
         * or deny access to protected resources accordingly. It allows you to extend and
         * customize the authentication process in your Spring Security configuration.
         */

        return security.build();
    }
}
