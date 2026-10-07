package com.pdv.pdv_backend.categoria.controller;

import com.pdv.pdv_backend.categoria.dto.request.CreateCategoriaRequestDto;
import com.pdv.pdv_backend.categoria.dto.response.ResponseCategoriaDto;
import com.pdv.pdv_backend.categoria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CategoriaController {
    private final CategoriaService categoriaService;


    @Operation(summary = "Crear una categoria de productos",
            description = "El nombre no puede repetirse.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria creada"),
            @ApiResponse(responseCode = "400", description = "Nombre vacío o inválido"),
            @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre")

    })
    @PostMapping
    public ResponseEntity<ResponseCategoriaDto> crear(@Valid @RequestBody CreateCategoriaRequestDto dto){
        ResponseCategoriaDto creada = categoriaService.crearCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCategoriaDto>> listar(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }


    @Operation(summary = "Eliminar una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminada"),
            @ApiResponse(responseCode = "404", description = "No existe"),
            @ApiResponse(responseCode = "409", description = "Tiene productos asociados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDto> editar( @PathVariable Long id, @Valid @RequestBody CreateCategoriaRequestDto dto){
        return ResponseEntity.ok(categoriaService.editarCategoria(id, dto));
    }

}
