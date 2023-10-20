package com.timife.prodatabase.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

///
 //.csrf().disable:This line disables CSRF (Cross-Site Request Forgery) protection.
 //                CSRF protection is often disabled for stateless JWT-based authentication.
 //.requestMatchers().permitAll://meaning permit all requests matching /auth path as it is
 //                either a login, authentication, or register api that doesn't need
 //                an authentication when requests are made.
 //.anyRequests().authenticated(): authenticate all other requests.
 //.addFilterBefore(jwtAuthFilter, UsernamePassFilter): This line adds the jwtAuthFilter before the UsernamePasswordAuthenticationFilter
 //                in the filter chain. The jwtAuthFilter is responsible for handling JWT-based authentication.

/**
 * This configuration class sets up JWT-based authentication and specifies the authorization
 * rules for various endpoints in a Spring Security context. It's well-suited for securing
 * a REST API where JWT tokens are used for authentication and authorization.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    //Contains a chain of filters and handles the different roles
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security
                .csrf()//
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider) //add an authentication provider,
                //needed any auth process
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        //
        // By adding your JwtAuthFilter before the UsernamePasswordAuthenticationFilter,
        // you can intercept and authenticate requests based on JWT tokens and then allow
        // or deny access to protected resources accordingly. It allows you to extend and
        // customize the authentication process in your Spring Security configuration.
        //

        return security.build();
    }
}
