package com.timife.prodatabase.services;

import com.timife.prodatabase.config.auth.JwtService;
import com.timife.prodatabase.domain.Role;
import com.timife.prodatabase.domain.User;
import com.timife.prodatabase.domain.requests.AuthRequest;
import com.timife.prodatabase.domain.requests.RegisterRequest;
import com.timife.prodatabase.domain.responses.AuthResponse;
import com.timife.prodatabase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * This code authenticates the user, by encoding the username
     * and password in UsernamePasswordAuthToken, a type of Authentication
     * if it authenticates successfully, it goes to the next line of
     * code which is to generate a new token and return it, to be
     * used for subsequent requests that require authorization.
     * @param request: Contains the username and password.
     * @return authResponse: which contains the generated token.
     */
    public AuthResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
