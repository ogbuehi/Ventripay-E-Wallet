package com.example.Testing_E_Wallet.service;

import com.example.Testing_E_Wallet.dto.mapper.SignUpRequestMapper;
import com.example.Testing_E_Wallet.dto.request.LoginRequest;
import com.example.Testing_E_Wallet.dto.request.SignUpRequest;
import com.example.Testing_E_Wallet.dto.response.CommandResponse;
import com.example.Testing_E_Wallet.dto.response.JwtResponse;
import com.example.Testing_E_Wallet.exception.ElementAlreadyExistsException;
import com.example.Testing_E_Wallet.exception.IncorrectPasswordException;
import com.example.Testing_E_Wallet.model.User;
import com.example.Testing_E_Wallet.repository.UserRepository;
import com.example.Testing_E_Wallet.security.JwtUtils;
import com.example.Testing_E_Wallet.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Testing_E_Wallet.common.Constants.*;

/**
 * Service used for Authentication related operations
 */
@Slf4j(topic = "AuthService")
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final SignUpRequestMapper signupRequestMapper;
    /**
     * Registers a user by provided credentials and user info
     *
     * @param request
     * @return id of the registered user
     */
    public CommandResponse signup(SignUpRequest request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername().trim()))
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_USER_NAME);
        if (userRepository.existsByEmailIgnoreCase(request.getEmail().trim()))
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_USER_EMAIL);
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new IncorrectPasswordException(INCORRECT_PASSWORD);

        final User user = signupRequestMapper.toEntity(request);
        userRepository.save(user);
        log.info(CREATED_USER, new Object[]{user.getUsername()});
        return CommandResponse.builder().id(user.getId()).build();
    }

    public JwtResponse login(LoginRequest request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername().trim(), request.getPassword().trim()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "";
        if (authentication.isAuthenticated()) {
            jwt = jwtUtils.generateJwtToken(request.getUsername());
        }

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        log.info(LOGGED_IN_USER, new Object[]{request.getUsername()});
        return JwtResponse
                .builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .roles(roles).build();
    }
}
