package com.pdv.pdv_backend.vendedores.controller.facade;

import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.service.facade.VendedorFacade;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/asistencia")
public class VendedorFacadeController {
    private final VendedorFacade vendedorFacade;

    public VendedorFacadeController(VendedorFacade vendedorFacade) {
        this.vendedorFacade = vendedorFacade;
    }


    @PostMapping("/{codigoBarras}/{sucursalId}")
    public TurnoResponseDto fichar(@PathVariable String codigoBarras, @PathVariable Long sucursalId) {
        return vendedorFacade.ficharPorCodigoBarras(codigoBarras, sucursalId);
    }
}
