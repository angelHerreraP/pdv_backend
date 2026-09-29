package com.pdv.pdv_backend.sucursal.service;

import com.pdv.pdv_backend.sucursal.dto.request.CreateSucursalRequestDto;
import com.pdv.pdv_backend.sucursal.dto.response.SucursalResponseDto;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository){
        this.sucursalRepository = sucursalRepository;
    }

    public SucursalResponseDto createSucursal(CreateSucursalRequestDto dto){
        if(dto.nombre().isBlank()){
            throw new IllegalArgumentException("Nel nombre de la sucursal no puede estar vacio.");
        }

        Sucursal nuevaSucursal = new Sucursal();
        nuevaSucursal.setNombre(dto.nombre());
        nuevaSucursal.setPlaza(dto.plaza());
        nuevaSucursal.setDireccion(dto.direccion());
        nuevaSucursal.setCreadoEn(LocalDateTime.now());
        nuevaSucursal = sucursalRepository.save(nuevaSucursal);
        return teResponseDto(nuevaSucursal);
    }

    public void eliminarSucural(Long id){
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Esa sucursal no existe."));
        sucursalRepository.deleteById(id);
    }

    public List<SucursalResponseDto> sucursales(){
        return sucursalRepository.findAll()
                .stream()
                .map(s -> new SucursalResponseDto(s.getId(), s.getNombre(), s.getPlaza(), s.getDireccion(),s.getCreadoEn()))
                .toList();
    }



    public SucursalResponseDto actualizarSucursal(Long id, CreateSucursalRequestDto dto){
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La Sucursal a editar no existe."));

        if (dto.nombre() != null && !dto.nombre().isBlank()) {
            sucursal.setNombre(dto.nombre());
        }
        if (dto.plaza() != null) {
            sucursal.setPlaza(dto.plaza());
        }
        if (dto.direccion() != null) {
            sucursal.setDireccion(dto.direccion());
        }

        sucursal = sucursalRepository.save(sucursal);
        return teResponseDto(sucursal);

    }



    private SucursalResponseDto teResponseDto(Sucursal sucursal){
        return new SucursalResponseDto(
                sucursal.getId(),
                sucursal.getNombre(),
                sucursal.getPlaza(),
                sucursal.getDireccion(),
                sucursal.getCreadoEn()
        );
    }

}
