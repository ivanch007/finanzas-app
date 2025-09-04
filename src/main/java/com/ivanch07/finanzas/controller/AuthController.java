package com.ivanch07.finanzas.controller;

import com.ivanch07.finanzas.dto.authDto.AuthenticationRequestDto;
import com.ivanch07.finanzas.dto.authDto.AuthenticationResponseDto;
import com.ivanch07.finanzas.dto.registerDto.RegisterRequestDto;
import com.ivanch07.finanzas.model.User;
import com.ivanch07.finanzas.security.JwtUtil;
import com.ivanch07.finanzas.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto requestDto) {
        try {
            User saved = userService.Register(requestDto);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.getEmail(),
                            authenticationRequestDto.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();





            String token = jwtUtil.generateToken(userDetails.getUsername());



            return ResponseEntity.ok(new AuthenticationResponseDto(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
