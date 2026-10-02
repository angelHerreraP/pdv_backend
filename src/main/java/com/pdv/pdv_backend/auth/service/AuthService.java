package com.pdv.pdv_backend.auth.service;

import com.pdv.pdv_backend.auth.dto.request.LoginRequestDto;
import com.pdv.pdv_backend.auth.dto.response.LoginResponseDto;
import com.pdv.pdv_backend.auth.entity.Usuario;
import com.pdv.pdv_backend.auth.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto dto){
        Usuario usuario = usuarioRepository.findByUsuario(dto.usuario())
                .orElseThrow(()-> new IllegalArgumentException("El usuario o contraseña son incorrectos."));
        if(!passwordEncoder.matches(dto.password(), usuario.getPasswordHash())){
            throw new IllegalArgumentException("El usuario o contraseña son incorrectos.");
        }
        String token = jwtService.generateToken(usuario.getUsuario(), usuario.getRol().getNombre(), usuario.getSucursal().getId());
        return new LoginResponseDto(token, usuario.getUsuario(), usuario.getRol().getNombre(), usuario.getSucursal().getNombre());
    }

    // TODO: remover durabilidad de TOKEN, POR ENDE, aqui va un RefreshToken
}
