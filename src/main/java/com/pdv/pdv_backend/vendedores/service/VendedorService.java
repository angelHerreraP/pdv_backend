package com.pdv.pdv_backend.vendedores.service;

import com.pdv.pdv_backend.config.exception.RecursoNoEncontradoException;
import com.pdv.pdv_backend.config.exception.VendedorException;
import com.pdv.pdv_backend.vendedores.dto.request.CrearVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.VendedorResponseDto;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.vendedores.repository.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public VendedorResponseDto crearVendedor(CrearVendedorRequestDto dto){
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(dto.nombre());
        vendedor.setCodigoBarras(generarCodigoBarras());
        vendedor.setSalarioSemanal(dto.salarioSemanal());
        vendedor.setDiaDescanso(dto.diaDescanso());
        vendedor.setActivo(true);
        vendedor = vendedorRepository.save(vendedor);
        return toResponseDto(vendedor);
    }

    public VendedorResponseDto modificarVendedor(Long id,UpdateVendedorRequestDto dto){
        Vendedor vendedor =  vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorException("El vendedor no existe."));

        if (dto.nombre() != null && !dto.nombre().isBlank()) {
            vendedor.setNombre(dto.nombre());
        }
        if (dto.salarioSemanal() != null) {
            vendedor.setSalarioSemanal(dto.salarioSemanal());
        }
        if (dto.diaDescanso() != null) {
            vendedor.setDiaDescanso(dto.diaDescanso());
        }
        if (dto.activo() != null) {
            vendedor.setActivo(dto.activo());
        }

        vendedor = vendedorRepository.save(vendedor);
        return toResponseDto(vendedor);
    }

    public List<VendedorResponseDto> vendedoresActivos(){
        return vendedorRepository.findByActivoTrue()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public VendedorResponseDto buscarPorCodigoBarras(String codigoBarras){
        Vendedor vendedor = vendedorRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new VendedorException("Código de barras no reconocido"));

        return toResponseDto(vendedor);
    }


    private VendedorResponseDto toResponseDto(Vendedor vendedor){
        return new VendedorResponseDto(
                vendedor.getId(),
                vendedor.getNombre(),
                vendedor.getCodigoBarras(),
                vendedor.getSalarioSemanal(),
                vendedor.getDiaDescanso(),
                vendedor.getActivo()
                );
    }

    private String generarCodigoBarras(){
        String codigo;
        do{
            codigo = "VEND-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        } while(vendedorRepository.existsByCodigoBarras(codigo));
        return codigo;
    }
}

//    private VendedorSucursalResponseDto toVendedorResponseDto(Vendedor vendedor){
//        return new VendedorSucursalResponseDto(
//                vendedor.getId(),
//                vendedor.getCodigoBarras(),
//                vendedor.getDiaDescanso()
//        );
//    }
