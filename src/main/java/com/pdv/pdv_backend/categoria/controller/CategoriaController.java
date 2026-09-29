package com.pdv.pdv_backend.categoria.controller;

import com.pdv.pdv_backend.categoria.dto.request.CreateCategoriaRequestDto;
import com.pdv.pdv_backend.categoria.dto.response.ResponseCategoriaDto;
import com.pdv.pdv_backend.categoria.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoriaDto createCategory(@RequestBody CreateCategoriaRequestDto dto){
        return categoriaService.crearCategoria(dto);
    }

    @GetMapping
    public List<ResponseCategoriaDto> listar(){
        return categoriaService.listarCategorias();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }

}
