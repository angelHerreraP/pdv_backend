package com.pdv.pdv_backend.proveedor.service;

import com.pdv.pdv_backend.config.exception.ProveedorException;
import com.pdv.pdv_backend.proveedor.dto.request.CreateProveedorRequestDto;
import com.pdv.pdv_backend.proveedor.dto.response.ProveedorResponseDto;
import com.pdv.pdv_backend.proveedor.entity.Proveedor;
import com.pdv.pdv_backend.proveedor.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public ProveedorResponseDto agregarProveedor(CreateProveedorRequestDto dto){
        if(proveedorRepository.findByNombre(dto.nombre()).isPresent()){
            throw new ProveedorException("El nombre de este proveedor ya esta registrado.");
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.nombre());
        proveedor.setNombreContacto(dto.nombreContacto());
        proveedor.setTelefonoContacto(dto.telefonoContacto());
        proveedor.setCreadoEn(LocalDateTime.now());
        proveedor = proveedorRepository.save(proveedor);
        return toResponseDto(proveedor);
    }

    public ProveedorResponseDto updateProveedor(Long proveedorId, CreateProveedorRequestDto dto){
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new ProveedorException("No se encontro este proveedor"));
        if(dto.nombre() != null && !dto.nombre().isBlank()){
            proveedor.setNombre(dto.nombre());
        }
        if(dto.nombreContacto() != null && !dto.nombreContacto().isBlank()){
            proveedor.setNombreContacto(dto.nombreContacto());
        }
        if(dto.telefonoContacto() != null && !dto.telefonoContacto().isBlank()){
            proveedor.setTelefonoContacto(dto.telefonoContacto());
        }
        proveedor = proveedorRepository.save(proveedor);
        return toResponseDto(proveedor);
    }

    public void deleteProveedor(Long proveedorId){
        proveedorRepository.deleteById(proveedorId);
    }

    public List<ProveedorResponseDto> listarProveedores(){
        return proveedorRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }


    private ProveedorResponseDto toResponseDto(Proveedor proveedor){
        return new ProveedorResponseDto(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getNombreContacto(),
                proveedor.getTelefonoContacto(),
                proveedor.getCreadoEn()
        );
    }
}
