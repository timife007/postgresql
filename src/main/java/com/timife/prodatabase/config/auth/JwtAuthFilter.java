package com.timife.prodatabase.config.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        //Get the authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        //To be extracted from the jwt
        final String userEmail;

        //Check if there is a valid token or any token at all(if it starts with Bearer)
        //if no, continue to the next event on the filter chain
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //initialize the jwt by getting it from the non-null header
        jwt = authHeader.substring(7);
        //initialize the userEmail by extracting it logically from the token.
        userEmail = jwtService.extractUsername(jwt);

        //Check if email is available but not authenticated(since context holder authentication is null.)
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            //checks the validity of the token and user details by providing the details from the
            //db using the userDetailsService.
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //this authToken is needed to update the securityContextHolder of the authorization state
                //of the user.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                //attach the details of our request with the authentication token
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                //Updates the SecurityContextHolder, allows it to know that the token is now authenticated.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //Continue to the next event in the filter chain.
        filterChain.doFilter(request, response);
    }
}
