package com.pdv.pdv_backend.vendedores.service.facade;

import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.sucursal.service.SucursalService;
import com.pdv.pdv_backend.vendedores.dto.request.CrearTurnoRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.vendedores.repository.TurnoVendedorRepository;
import com.pdv.pdv_backend.vendedores.repository.VendedorRepository;
import com.pdv.pdv_backend.vendedores.service.TurnoService;
import com.pdv.pdv_backend.vendedores.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendedorFacade {
    private final VendedorService vendedorService;
    private final TurnoService turnoService;
    private final SucursalService sucursalService;



    //Sabe quien y en donde entro a trabajhar
    public TurnoResponseDto ficharPorCodigoBarras(String codigoBarras, Long sucursalId){
        Vendedor vendedor = vendedorService.obtenerPorCodigo(codigoBarras);
        if(turnoService.tieneTurnoAbierto(vendedor.getId())){
            return turnoService.marcarSalida(vendedor.getId());
        }
        Sucursal sucursal = sucursalService.encontrarPorId(sucursalId);
        return turnoService.marcarEntrada(vendedor, sucursal);

    }


}
