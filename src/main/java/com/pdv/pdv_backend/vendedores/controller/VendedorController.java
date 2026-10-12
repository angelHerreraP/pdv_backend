package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.request.CrearVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.VendedorResponseDto;
import com.pdv.pdv_backend.vendedores.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vendedores")
@RestController
@RequiredArgsConstructor
@Tag(name = "Vendedores", description = "Gestion de vendedores en el Punto de venta.")
public class VendedorController {
    private final VendedorService vendedorService;

    @Operation(summary = "Crear un vendedor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vendedor creada"),
            @ApiResponse(responseCode = "400", description = "parametro vacío o inválido")
    })
    @PostMapping
    public ResponseEntity<VendedorResponseDto> crear(@RequestBody CrearVendedorRequestDto dto){
        VendedorResponseDto v = vendedorService.crearVendedor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(v);
    }

    @Operation(summary = "Modificar un vendedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vendedor modificado con exito"),
            @ApiResponse(responseCode = "204", description = "No encontre al vendedor a editar."),
            @ApiResponse(responseCode = "400", description = "parametro vacío o inválido")
    })
    @PatchMapping("/{vendedorId}")
    public ResponseEntity<VendedorResponseDto> modificar(@RequestBody UpdateVendedorRequestDto dto, @PathVariable Long vendedorId){
        return ResponseEntity.ok(vendedorService.modificarVendedor(vendedorId, dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendedorResponseDto>> listarTodos(){
        return ResponseEntity.ok(vendedorService.vendedoresActivos());
    }

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<VendedorResponseDto> buscarVendedor(@PathVariable String codigoBarras){
        return ResponseEntity.ok(vendedorService.buscarPorCodigoBarras(codigoBarras));
    }
}
