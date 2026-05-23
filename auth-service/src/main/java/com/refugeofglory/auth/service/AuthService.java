package com.refugeofglory.auth.service;

import com.refugeofglory.auth.model.Role;
import com.refugeofglory.auth.model.User;
import com.refugeofglory.auth.model.dto.TokenResponse;
import com.refugeofglory.auth.model.dto.UserDTO;
import com.refugeofglory.auth.repository.UserRepository;
import com.refugeofglory.auth.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public User register(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já cadastrado");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(Role.PLAYER);

        return userRepository.save(user);
    }

    public TokenResponse login(UserDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtProvider.generateToken(user);
        return new TokenResponse(token, "Bearer", 86400000L);
    }

    public Claims validateToken(String token) {
        if (!jwtProvider.validateToken(token)) {
            throw new RuntimeException("Token inválido");
        }
        return jwtProvider.extractClaims(token);
    }
}