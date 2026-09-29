package com.pdv.pdv_backend.auth.controller;

import com.pdv.pdv_backend.auth.dto.request.LoginRequestDto;
import com.pdv.pdv_backend.auth.dto.response.LoginResponseDto;
import com.pdv.pdv_backend.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto){
        return authService.login(dto);
    }
}
