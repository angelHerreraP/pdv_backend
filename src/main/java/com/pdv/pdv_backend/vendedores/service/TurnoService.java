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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurnoService {
    private final TurnoVendedorRepository turnoVendedorRepository;
    private final VendedorRepository vendedorRepository;
    private final SucursalRepository sucursalRepository;

    public TurnoService(TurnoVendedorRepository turnoVendedorRepository, VendedorRepository vendedorRepository, SucursalRepository sucursalRepository) {
        this.turnoVendedorRepository = turnoVendedorRepository;
        this.vendedorRepository = vendedorRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public TurnoResponseDto marcarEntrada(CrearTurnoRequestDto dto){
        Vendedor vendedor = vendedorRepository.findById(dto.vendedorId())
                .orElseThrow(() -> ApiException.noEncontrado("EL vendedor no existe."));
        Sucursal sucursal = sucursalRepository.findById(dto.sucursalId())
                .orElseThrow(() -> ApiException.noEncontrado("La sucursal no existe."));
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
    public TurnoResponseDto marcarSalida(Long vendedorId ){
        TurnoVendedor turnoVendedor = turnoVendedorRepository.findByVendedorIdAndHoraSalidaIsNull(vendedorId)
                .orElseThrow(() -> ApiException.conflicto("El vendedor no tiene un turno activo."));
        turnoVendedor.setHoraSalida(LocalDateTime.now());
        turnoVendedor = turnoVendedorRepository.save(turnoVendedor);
        return toResponseDto(turnoVendedor);
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
