package org.chime.chime.services;

import lombok.RequiredArgsConstructor;
import org.chime.chime.dto.request.LoginRequest;
import org.chime.chime.dto.request.SignUpRequest;
import org.chime.chime.dto.response.AuthResponse;
import org.chime.chime.dto.response.UserResponse;
import org.chime.chime.entities.User;
import org.chime.chime.exceptions.InvalidCredentialsException;
import org.chime.chime.exceptions.UserAlreadyExistsException;
import org.chime.chime.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(SignUpRequest request) {
        if (!request.isPasswordMatching()) {
            throw new InvalidCredentialsException("Passwords do not match");
        }

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new UserAlreadyExistsException("User '"+ request.username() + "' already exists");
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        try {
            User savedUser = userRepository.save(user);

            var userDetails = userService.loadUserByUsername(savedUser.getUsername());
            String token = jwtService.generateToken(userDetails);

            UserResponse userResponse = UserResponse.from(savedUser);

            return AuthResponse.success("User registered successfully", userResponse, token);
        } catch (Exception e) {
            return AuthResponse.failure("Registration failed: " + e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            Optional<User> userOpt;
            if (request.usernameOrEmail().contains("@")) {
                userOpt = userRepository.findByEmail(request.usernameOrEmail());
            } else {
                userOpt = userRepository.findByUsername(request.usernameOrEmail());
            }

            if (userOpt.isEmpty()) {
                throw new InvalidCredentialsException("User not found");
            }

            User user = userOpt.get();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            request.password()
                    )
            );

            var userDetails = userService.loadUserByUsername(user.getUsername());
            String token = jwtService.generateToken(userDetails);

            UserResponse userResponse = UserResponse.from(user);

            return AuthResponse.success("Login successful", userResponse, token);

        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        } catch (Exception e) {
            return AuthResponse.failure("Login failed: " + e.getMessage());
        }
    }
}