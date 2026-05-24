package com.refugeofglory.auth.controller;

import com.refugeofglory.auth.model.User;
import com.refugeofglory.auth.model.dto.TokenResponse;
import com.refugeofglory.auth.model.dto.UserDTO;
import com.refugeofglory.auth.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("/validate")
    public ResponseEntity<Claims> validate(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        return ResponseEntity.ok(authService.validateToken(jwt));
    }
}