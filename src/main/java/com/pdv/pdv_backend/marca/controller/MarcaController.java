package com.pdv.pdv_backend.marca.controller;

import com.pdv.pdv_backend.marca.dto.response.MarcaResponseDto;
import com.pdv.pdv_backend.marca.dto.request.CreateMarcaRequestDto;
import com.pdv.pdv_backend.marca.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    //crear nueva marca
    @Operation(summary = "Crear una marca de productos",
    description = "El nombre no puede repetirse.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "marca creada"),
            @ApiResponse(responseCode = "400", description = "Nombre vacío o inválido"),
            @ApiResponse(responseCode = "409", description = "Ya existe una marca con ese nombre")

    })
    @PostMapping
    public ResponseEntity<MarcaResponseDto> crear(@Valid @RequestBody CreateMarcaRequestDto dto){
        MarcaResponseDto marca = marcaService.crearMarca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(marca);
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDto>> listar(){
        return ResponseEntity.ok(marcaService.listarMarcas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDto> obtener(@PathVariable Long id){
        MarcaResponseDto marcaResponseDto = marcaService.obtenerMarca(id);
        return ResponseEntity.ok(marcaResponseDto);
    }

    @Operation(summary = "Eliminar una marca")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminada"),
            @ApiResponse(responseCode = "404", description = "No existe"),
            @ApiResponse(responseCode = "409", description = "Tiene productos asociados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDto> editar(@PathVariable Long id, @Valid @RequestBody CreateMarcaRequestDto dto){
        return ResponseEntity.ok(marcaService.editarMarca(id, dto));
    }
}
