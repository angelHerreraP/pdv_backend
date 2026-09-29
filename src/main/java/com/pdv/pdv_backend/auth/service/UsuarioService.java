package com.pdv.pdv_backend.auth.service;

import com.pdv.pdv_backend.auth.dto.request.CreateUsuarioRequestDto;
import com.pdv.pdv_backend.auth.dto.request.UpdateUsuarioRequestDto;
import com.pdv.pdv_backend.auth.dto.response.UserResponseDto;
import com.pdv.pdv_backend.auth.entity.Rol;
import com.pdv.pdv_backend.auth.entity.Usuario;
import com.pdv.pdv_backend.auth.repository.RolRepository;
import com.pdv.pdv_backend.auth.repository.UsuarioRepository;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final SucursalRepository sucursalRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder,
                          SucursalRepository sucursalRepository){
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder=passwordEncoder;
        this.sucursalRepository = sucursalRepository;
    }

    public UserResponseDto crearUsuario(CreateUsuarioRequestDto dto){
        Rol rol = rolRepository.findById(dto.rolId())
                .orElseThrow(() -> new IllegalArgumentException("No existe el rol a asignar"));

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.nombre());
        nuevoUsuario.setUsuario(dto.usuario());
        nuevoUsuario.setPasswordHash(passwordEncoder.encode(dto.password()));
        nuevoUsuario.setRol(rol);
        nuevoUsuario.setCreadoEn(LocalDateTime.now());


        if(dto.sucursalId() != null){
            Sucursal sucursal = sucursalRepository.findById(dto.sucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("La sucursal que intentas asignar no existe."));
            nuevoUsuario.setSucursal(sucursal);
        }
        nuevoUsuario = usuarioRepository.save(nuevoUsuario);
        return toResponseDto(nuevoUsuario);

    }

    public List<UserResponseDto> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public UserResponseDto editarUsuario(Long id, UpdateUsuarioRequestDto dto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario al que intenta modificar no existe."));
        if (dto.nombre() != null && !dto.nombre().isBlank()) {
            usuario.setNombre(dto.nombre());
        }

        if (dto.password() != null && !dto.password().isBlank()) {
            usuario.setPasswordHash(passwordEncoder.encode(dto.password()));
        }

        if (dto.rolId() != null) {
            Rol rol = rolRepository.findById(dto.rolId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
            usuario.setRol(rol);
        }

        if (dto.sucursalId() != null) {
            Sucursal sucursal = sucursalRepository.findById(dto.sucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));
            usuario.setSucursal(sucursal);
        }

        usuario = usuarioRepository.save(usuario);
        return toResponseDto(usuario);
    }

    public void deleteUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario a eliminar no existe"));
        usuarioRepository.deleteById(id);
    }

    private UserResponseDto toResponseDto(Usuario u){
        return new UserResponseDto(
                u.getId(),
                u.getNombre(),
                u.getUsuario(),
                u.getRol().getNombre(),
                u.getSucursal() != null ? u.getSucursal().getId() : null,
                u.getSucursal() != null ? u.getSucursal().getNombre() : null
        );
    }
}
