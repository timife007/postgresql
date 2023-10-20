package com.timife.prodatabase.controllers;

import com.timife.prodatabase.domain.requests.AuthRequest;
import com.timife.prodatabase.domain.requests.RegisterRequest;
import com.timife.prodatabase.domain.responses.AuthResponse;
import com.timife.prodatabase.domain.responses.RegisterResponse;
import com.timife.prodatabase.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
            ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
            ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
