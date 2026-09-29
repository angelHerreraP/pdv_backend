package com.pdv.pdv_backend.marca.service;

import com.pdv.pdv_backend.categoria.dto.response.ResponseCategoriaDto;
import com.pdv.pdv_backend.marca.dto.request.MarcaResponseDto;
import com.pdv.pdv_backend.marca.dto.response.CreateMarcaRequestDto;
import com.pdv.pdv_backend.marca.entity.Marca;
import com.pdv.pdv_backend.marca.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public MarcaResponseDto crearMarca(CreateMarcaRequestDto dto){
        if (dto.nombre() == null || dto.nombre().isBlank()){
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacio");
        }

        Marca nuevaMarca = new Marca();
        nuevaMarca.setNombre(dto.nombre());
        nuevaMarca = marcaRepository.save(nuevaMarca);
        return new MarcaResponseDto(nuevaMarca.getId(), nuevaMarca.getNombre());
    }

    public void eliminarMarca(Long id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La marca a eliminar no existe"));
        marcaRepository.delete(marca);
    }

    public List<MarcaResponseDto> listarMarcas(){
        return marcaRepository.findAll()
                .stream()
                .map(m -> new MarcaResponseDto(m.getId(), m.getNombre()))
                .toList();
    }
}
