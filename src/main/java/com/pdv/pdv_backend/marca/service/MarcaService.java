package com.pdv.pdv_backend.marca.service;

import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.marca.dto.response.MarcaResponseDto;
import com.pdv.pdv_backend.marca.dto.request.CreateMarcaRequestDto;
import com.pdv.pdv_backend.marca.entity.Marca;
import com.pdv.pdv_backend.marca.repository.MarcaRepository;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {
    private final MarcaRepository marcaRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    public MarcaResponseDto crearMarca(CreateMarcaRequestDto dto){
        if(marcaRepository.existsByNombre(dto.nombre())){
            throw ApiException.conflicto("Ya existe una marca con ese nombre");
        }
        Marca nuevaMarca = new Marca();
        nuevaMarca.setNombre(dto.nombre());
        nuevaMarca = marcaRepository.save(nuevaMarca);
        return toResponse(nuevaMarca);
    }

    public void eliminarMarca(Long id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> ApiException.noEncontrado("La marca a eliminar no existe"));

        if(productoRepository.existsByMarcaId(id)){
            throw ApiException.conflicto("Esta Marca contiene Productos Asociados");
        }
        marcaRepository.delete(marca);
    }

    public List<MarcaResponseDto> listarMarcas(){
        return marcaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MarcaResponseDto obtenerMarca(Long id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> ApiException.noEncontrado("No eciste una marca con ese id."));
        return toResponse(marca);
    }

    @Transactional
    public MarcaResponseDto editarMarca(Long id,CreateMarcaRequestDto dto){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> ApiException.noEncontrado("No se encontro la marca"));
        if(marcaRepository.existsByNombreAndIdNot(dto.nombre(), id)){
            throw ApiException.conflicto("Ya existe una marca con ese nombre");
        }
        marca.setNombre(dto.nombre());
        return toResponse(marca);
    }

    private MarcaResponseDto toResponse(Marca m) {
        return new MarcaResponseDto(m.getId(), m.getNombre());
    }
}
