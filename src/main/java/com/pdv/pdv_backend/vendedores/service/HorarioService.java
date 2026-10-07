package com.pdv.pdv_backend.vendedores.service;

import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.vendedores.dto.request.CrearHorarioRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateHorarioRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.HorarioResponseDto;
import com.pdv.pdv_backend.vendedores.entity.HorarioVendedor;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.vendedores.repository.HorarioVendedorRepository;
import com.pdv.pdv_backend.vendedores.repository.VendedorRepository;
import org.springframework.stereotype.Service;

@Service
public class HorarioService {

    private final HorarioVendedorRepository horarioVendedorRepository;
    private final VendedorRepository vendedorRepository;

    public HorarioService(HorarioVendedorRepository horarioVendedorRepository, VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
        this.horarioVendedorRepository = horarioVendedorRepository;
    }

    public HorarioResponseDto crearHorario(CrearHorarioRequestDto dto){
        if(horarioVendedorRepository.findByVendedorId(dto.vendedorId()).isPresent()){
            throw ApiException.conflicto("El vendedor ya tiene un horario asignado");
        }
        Vendedor vendedor = vendedorRepository.findById(dto.vendedorId())
                .orElseThrow(() -> ApiException.noEncontrado("Vendedor no encontrado."));
        HorarioVendedor horario = new HorarioVendedor();
        horario.setVendedor(vendedor);
        horario.setHoraEntradaEsperada(dto.horaEntradaEsperada());
        horario.setHoraSalidaEsperada(dto.horaSalidaEsperada());
        horario = horarioVendedorRepository.save(horario);
        return toResponseDto(horario);


    }

    public HorarioResponseDto editarHorario(Long vendedorId, UpdateHorarioRequestDto dto){
        HorarioVendedor horarioVendedor = horarioVendedorRepository.findByVendedorId(vendedorId)
                .orElseThrow(() -> ApiException.noEncontrado("El vendedor al que se le se quiere modificar el horario no existe."));
        if(dto.horaEntradaEsperada()!= null ){
            horarioVendedor.setHoraEntradaEsperada(dto.horaEntradaEsperada());
        }
        if(dto.horaSalidaEsperada()!= null ){
            horarioVendedor.setHoraSalidaEsperada(dto.horaSalidaEsperada());
        }
        horarioVendedor = horarioVendedorRepository.save(horarioVendedor);
        return toResponseDto(horarioVendedor);
    }


    private HorarioResponseDto toResponseDto(HorarioVendedor horarioVendedor){
        return new HorarioResponseDto(
                horarioVendedor.getId(),
                horarioVendedor.getVendedor().getId(),
                horarioVendedor.getHoraEntradaEsperada(),
                horarioVendedor.getHoraSalidaEsperada()
        );
    }
}
