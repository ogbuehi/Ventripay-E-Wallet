package com.example.ogbuehi.controller;


import com.example.ogbuehi.dto.request.LoginRequest;
import com.example.ogbuehi.dto.request.SignUpRequest;
import com.example.ogbuehi.dto.response.ApiResponse;
import com.example.ogbuehi.dto.response.CommandResponse;
import com.example.ogbuehi.dto.response.JwtResponse;
import com.example.ogbuehi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.Instant;

import static com.example.ogbuehi.common.Constants.SUCCESS;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/userAuth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final Clock clock;

    /**
     * Registers users using their credentials and user info
     *
     * @param request
     * @return id of the registered user
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<CommandResponse>> signup(@Valid @RequestBody SignUpRequest request) {
        final CommandResponse response = authService.signup(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));

    }

    /**
     * Authenticates users by their credentials
     *
     * @param request
     * @return JwtResponse
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest request) {
        final JwtResponse response = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }
}
