package com.pdv.pdv_backend.categoria.service;

import com.pdv.pdv_backend.categoria.dto.request.CreateCategoriaRequestDto;
import com.pdv.pdv_backend.categoria.dto.response.ResponseCategoriaDto;
import com.pdv.pdv_backend.categoria.entity.Categoria;
import com.pdv.pdv_backend.categoria.repository.CategoriaRepository;
import com.pdv.pdv_backend.config.exception.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseCategoriaDto crearCategoria(CreateCategoriaRequestDto dto){
        if (dto.nombre() == null || dto.nombre().isBlank()){
            throw  ApiException.invalido("El nombre de la categoria no puede estar vacio");
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
}
