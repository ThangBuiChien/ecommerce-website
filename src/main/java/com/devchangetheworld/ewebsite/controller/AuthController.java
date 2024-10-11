package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.request.LoginRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.ApiResponse;
import com.devchangetheworld.ewebsite.dto.response.JwtResponseDTO;
import com.devchangetheworld.ewebsite.security.jwt.JwtUntils;
import com.devchangetheworld.ewebsite.security.user.EShopUserDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUntils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokenForUser(authentication);
            EShopUserDetail userDetails = (EShopUserDetail) authentication.getPrincipal();
            JwtResponseDTO jwtResponse = new JwtResponseDTO(userDetails.getId(), jwt);
            return ResponseEntity.ok(new ApiResponse(200, "Login Success!", jwtResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(403, e.getMessage(), null));
        }

    }
}
