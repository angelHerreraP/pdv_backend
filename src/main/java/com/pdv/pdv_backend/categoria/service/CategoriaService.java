package com.pdv.pdv_backend.categoria.service;

import com.pdv.pdv_backend.categoria.dto.request.CreateCategoriaRequestDto;
import com.pdv.pdv_backend.categoria.dto.response.ResponseCategoriaDto;
import com.pdv.pdv_backend.categoria.entity.Categoria;
import com.pdv.pdv_backend.categoria.repository.CategoriaRepository;
import com.pdv.pdv_backend.config.exception.ApiException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public ResponseCategoriaDto crearCategoria(CreateCategoriaRequestDto dto){
        if(categoriaRepository.existsByNombre(dto.nombre())){
            throw ApiException.conflicto("Ya existe una categoria con ese nombre.");
        }
        Categoria categoriaNueva = new Categoria();
        categoriaNueva.setNombre(dto.nombre());
        categoriaNueva = categoriaRepository.save(categoriaNueva);
        return new ResponseCategoriaDto(categoriaNueva.getId(), categoriaNueva.getNombre());
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> ApiException.noEncontrado("Ese id no existe."));
        categoriaRepository.delete(categoria);
    }

    public List<ResponseCategoriaDto> listarCategorias(){
        return categoriaRepository.findAll()
                .stream()
                .map(c -> new ResponseCategoriaDto(c.getId(), c.getNombre()))
                .toList();
    }

    @Transactional
    public ResponseCategoriaDto editarCategoria(Long categoriaId, CreateCategoriaRequestDto dto){
        Categoria  c = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> ApiException.noEncontrado("La categoria no existe."));
        if(categoriaRepository.existsByNombreAndIdNot(dto.nombre(), categoriaId)){
            throw ApiException.conflicto("Ya existe una categoria con ese nombre.");
        }
        c.setNombre(dto.nombre());
        return new ResponseCategoriaDto(c.getId(), c.getNombre());
    }
}
