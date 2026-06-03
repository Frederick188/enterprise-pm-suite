package com.uditray.enterprise.service;

import com.uditray.enterprise.dto.*;
import com.uditray.enterprise.entity.Organization;
import com.uditray.enterprise.entity.User;
import com.uditray.enterprise.repository.OrganizationRepository;
import com.uditray.enterprise.repository.UserRepository;
import com.uditray.enterprise.security.JwtService;
import com.uditray.enterprise.security.SecurityUtils;
import com.uditray.enterprise.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        Organization organization =
                organizationRepository.findById(
                        request.getOrganizationId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Organization not found"
                        )
                );

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .role(request.getRole())
                .organization(organization)
                .build();

        userRepository.save(user);

        return jwtService.generateToken(user.getEmail());
    }

    public String login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(request.getEmail());
    }

    // ✅ CORRECT PLACE (OUTSIDE login, INSIDE class)
    public User getCurrentUser() {
        return userRepository.findByEmail(
                SecurityUtils.getCurrentUserEmail()
        ).orElseThrow();
    }
}