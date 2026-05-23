package com.homeo.clinic.controller;
import org.springframework.http.ResponseEntity;
import com.homeo.clinic.dto.AuthRequest;
import com.homeo.clinic.dto.AuthResponse;
import com.homeo.clinic.entity.User;
import com.homeo.clinic.repository.UserRepository;
import com.homeo.clinic.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.homeo.clinic.dto.SignupRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    // SIGNUP API
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody SignupRequest request
    ) {

        try {

            // Check existing username
            if (
                    userRepository.existsByUsername(
                            request.getUsername()
                    )
            ) {

                return ResponseEntity.ok(
                        "Username already exists"
                );
            }

            if (
                    userRepository.existsByEmail(
                            request.getEmail()
                    )
            ) {

                return ResponseEntity.ok(
                        "Email already exists"
                );
            }

            User user = new User();

            user.setFullName(
                    request.getFullName()
            );

            user.setUsername(
                    request.getUsername()
            );

            user.setEmail(
                    request.getEmail()
            );

            user.setPhoneNumber(
                    request.getPhoneNumber()
            );

            // ENCODE PASSWORD
            user.setPassword(
                    passwordEncoder.encode(
                            request.getPassword()
                    )
            );

            user.setRole("DOCTOR");

            user.setActive(true);

            userRepository.save(user);

            return ResponseEntity.ok(
                    "Signup successful"
            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.ok(
                    "ERROR: " + e.getMessage()
            );
        }
    }

    // LOGIN API
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username"));

        // Check password
        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}