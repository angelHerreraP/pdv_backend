package com.pdv.pdv_backend.vendedores.controller.facade;

import com.pdv.pdv_backend.vendedores.dto.request.AsistenciaRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.service.facade.VendedorFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/asistencia")
@RequiredArgsConstructor
public class VendedorFacadeController {
    private final VendedorFacade vendedorFacade;




    @PostMapping
    public ResponseEntity<TurnoResponseDto> fichar(@RequestBody @Valid AsistenciaRequestDto dto) {
        return ResponseEntity.ok(vendedorFacade.ficharPorCodigoBarras(dto.codigoBarras(), dto.sucursalId()));
    }
}
