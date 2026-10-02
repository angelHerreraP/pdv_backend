package com.pdv.pdv_backend.vendedores.service.facade;

import com.pdv.pdv_backend.config.exception.VendedorException;
import com.pdv.pdv_backend.vendedores.dto.request.CrearTurnoRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.vendedores.repository.TurnoVendedorRepository;
import com.pdv.pdv_backend.vendedores.repository.VendedorRepository;
import com.pdv.pdv_backend.vendedores.service.TurnoService;
import org.springframework.stereotype.Service;

@Service
public class VendedorFacade {
    private final VendedorRepository vendedorRepository;
    private final TurnoService turnoService;
    private final TurnoVendedorRepository turnoVendedorRepository;

    public VendedorFacade(VendedorRepository vendedorRepository, TurnoService turnoService, TurnoVendedorRepository turnoVendedorRepository) {
        this.vendedorRepository = vendedorRepository;
        this.turnoService = turnoService;
        this.turnoVendedorRepository = turnoVendedorRepository;
    }

    public TurnoResponseDto ficharPorCodigoBarras(String codigoBarras, Long sucursalId){
        Vendedor vendedor = vendedorRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new VendedorException("El codigo de barras no existe"));
        if(!vendedor.getActivo()){
            throw new VendedorException("Este vendedor no labora actualmente con nosotros");
        }
        return turnoVendedorRepository.findByVendedorIdAndHoraSalidaIsNull(vendedor.getId())
                .map(turnoActivo -> turnoService.marcarSalida(vendedor.getId()))
                .orElseGet(() -> turnoService.marcarEntrada(new CrearTurnoRequestDto(vendedor.getId(), sucursalId)));
    }
}
