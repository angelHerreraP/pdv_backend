package com.pdv.pdv_backend.marca.controller;

import com.pdv.pdv_backend.marca.dto.request.MarcaResponseDto;
import com.pdv.pdv_backend.marca.dto.response.CreateMarcaRequestDto;
import com.pdv.pdv_backend.marca.service.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;


    public MarcaController(MarcaService marcaService){
        this.marcaService = marcaService;
    }

    //crear nueva marca
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MarcaResponseDto createMarca(@RequestBody CreateMarcaRequestDto dto){
        return marcaService.crearMarca(dto);
    }

    @GetMapping
    public List<MarcaResponseDto> getMarcas(){
        return marcaService.listarMarcas();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMarca(@PathVariable Long id){
        marcaService.eliminarMarca(id);
    }

}
