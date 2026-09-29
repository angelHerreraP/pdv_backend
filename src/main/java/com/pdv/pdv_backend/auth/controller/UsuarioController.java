package com.pdv.pdv_backend.auth.controller;

import com.pdv.pdv_backend.auth.dto.request.CreateUsuarioRequestDto;
import com.pdv.pdv_backend.auth.dto.request.UpdateUsuarioRequestDto;
import com.pdv.pdv_backend.auth.dto.response.UserResponseDto;
import com.pdv.pdv_backend.auth.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto crearUsuario(@RequestBody CreateUsuarioRequestDto dto) {
       return usuarioService.crearUsuario(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }

    @GetMapping
    public List<UserResponseDto> usuarios(){
        return usuarioService.listarUsuarios();
    }

    @PatchMapping("/{id}")
    public UserResponseDto editarUsuario(@PathVariable Long id, @RequestBody UpdateUsuarioRequestDto dto){
        return usuarioService.editarUsuario(id, dto);
    }


}