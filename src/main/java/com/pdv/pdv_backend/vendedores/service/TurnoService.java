package com.pdv.pdv_backend.vendedores.service;

import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import com.pdv.pdv_backend.vendedores.dto.request.CrearTurnoRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.entity.TurnoVendedor;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.vendedores.repository.TurnoVendedorRepository;
import com.pdv.pdv_backend.vendedores.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {
    private final TurnoVendedorRepository turnoVendedorRepository;


    @Transactional
    public TurnoResponseDto marcarEntrada(Vendedor vendedor, Sucursal sucursal){
        if (turnoVendedorRepository.findByVendedorIdAndHoraSalidaIsNull(vendedor.getId()).isPresent()) {
            throw ApiException.conflicto("El vendedor ya tiene un turno activo, debe cerrarlo primero.");
        }
        TurnoVendedor turnoVendedor = new TurnoVendedor();
        turnoVendedor.setVendedor(vendedor);
        turnoVendedor.setSucursal(sucursal);
        turnoVendedor.setHoraEntrada(LocalDateTime.now());
        turnoVendedor.setHoraSalida(null);
        turnoVendedor = turnoVendedorRepository.save(turnoVendedor);
        return toResponseDto(turnoVendedor);
    }

    @Transactional
    public TurnoResponseDto marcarSalida(Long vendedorId ){
        TurnoVendedor turnoVendedor = obtenerTurnoAbierto(vendedorId);
        turnoVendedor.setHoraSalida(LocalDateTime.now());
        return toResponseDto(turnoVendedorRepository.save(turnoVendedor));
    }

    public TurnoVendedor obtenerTurnoAbierto(Long vendedorId){
        return turnoVendedorRepository.findByVendedorIdAndHoraSalidaIsNull(vendedorId)
                .orElseThrow(() -> ApiException.conflicto("El vendedor no tiene turno abierto en esta sucursal"));
    }

    public List<TurnoResponseDto> listarTodosLosVendedoresActivos(){
        return turnoVendedorRepository.findByHoraSalidaIsNull()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public List<TurnoResponseDto> listarVendedoresActivosEnSucursal(Long sucursalId){
         return turnoVendedorRepository.findBySucursalIdAndHoraSalidaIsNull(sucursalId)
                 .stream()
                 .map(this::toResponseDto)
                 .toList();
    }

    public boolean tieneTurnoAbierto(Long vendedorId){
        return turnoVendedorRepository.findByVendedorIdAndHoraSalidaIsNull(vendedorId).isPresent();
    }

    private TurnoResponseDto toResponseDto(TurnoVendedor turnoVendedor){
        return new TurnoResponseDto(
                turnoVendedor.getId(),
                turnoVendedor.getVendedor().getId(),
                turnoVendedor.getSucursal().getId(),
                turnoVendedor.getHoraEntrada(),
                turnoVendedor.getHoraSalida()
        );
    }
}
