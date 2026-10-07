package com.pdv.pdv_backend.categoria.service;

import com.pdv.pdv_backend.categoria.dto.request.CreateCategoriaRequestDto;
import com.pdv.pdv_backend.categoria.dto.response.ResponseCategoriaDto;
import com.pdv.pdv_backend.categoria.entity.Categoria;
import com.pdv.pdv_backend.categoria.repository.CategoriaRepository;
import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.marca.dto.response.MarcaResponseDto;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    public ResponseCategoriaDto crearCategoria(CreateCategoriaRequestDto dto){
        if(categoriaRepository.existsByNombre(dto.nombre())){
            throw ApiException.conflicto("Ya existe una categoria con ese nombre.");
        }
        Categoria categoriaNueva = new Categoria();
        categoriaNueva.setNombre(dto.nombre());
        categoriaNueva = categoriaRepository.save(categoriaNueva);
        return toResponse(categoriaNueva);
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> ApiException.noEncontrado("Ese id no existe."));

        if(productoRepository.existsByCategoriaId(id)){
            throw ApiException.conflicto("No se puede eliminar: tiene productos asociados");
        }
        categoriaRepository.delete(categoria);
    }

    public List<ResponseCategoriaDto> listarCategorias(){
        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ResponseCategoriaDto obtenerCategoria(Long id){
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> ApiException.noEncontrado("No es encontro la categoria."));
        return toResponse(c);
    }

    @Transactional
    public ResponseCategoriaDto editarCategoria(Long categoriaId, CreateCategoriaRequestDto dto){
        Categoria  c = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> ApiException.noEncontrado("La categoria no existe."));
        if(categoriaRepository.existsByNombreAndIdNot(dto.nombre(), categoriaId)){
            throw ApiException.conflicto("Ya existe una categoria con ese nombre.");
        }
        c.setNombre(dto.nombre());
        return toResponse(c);
    }

    private ResponseCategoriaDto toResponse(Categoria categoria){
        return new ResponseCategoriaDto(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}
