package com.pdv.pdv_backend.inventario.service;

import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.inventario.dto.request.AjustarInventarioDto;
import com.pdv.pdv_backend.inventario.dto.response.InventarioResponseDto;
import com.pdv.pdv_backend.inventario.entity.Inventario;
import com.pdv.pdv_backend.inventario.repository.InventarioRepository;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public InventarioService(InventarioRepository inventarioRepository, ProductoRepository productoRepository, SucursalRepository sucursalRepository) {
        this.inventarioRepository = inventarioRepository;
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public List<InventarioResponseDto> inventarioDeSucursal(Long sucursalId, String rol, Long sucursalIdDelToken) {
        if (rol.equals("sucursal") && !sucursalIdDelToken.equals(sucursalId)) {
            throw ApiException.noAutorizado("No puedes ver el inventario de otra sucursal");
        }
        return inventarioRepository.findBySucursalId(sucursalId)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public InventarioResponseDto aumentarInventario(AjustarInventarioDto dto){
        Inventario inventarioActual = inventarioRepository.findByProductoIdAndSucursalId(dto.productoId(), dto.sucursalId())
            .orElseGet(()->{
                Inventario nuevo = new Inventario();nuevo.setProducto(productoRepository.getReferenceById(dto.productoId()));
                nuevo.setSucursal(sucursalRepository.getReferenceById(dto.sucursalId()));
                nuevo.setCantidad(0);
                return nuevo;
            });
        inventarioActual.setCantidad(inventarioActual.getCantidad() +dto.cantidad());
        return toResponseDto(inventarioRepository.save(inventarioActual));
    }
    public InventarioResponseDto reducirInventario(AjustarInventarioDto dto){
        Inventario inventarioActual = inventarioRepository.findByProductoIdAndSucursalId(dto.productoId(), dto.sucursalId())
                .orElseThrow(() -> ApiException.conflicto("El producto que intentas reducir, no tiene stock o no existe."));
        if(inventarioActual.getCantidad()  < dto.cantidad()) {
            throw ApiException.conflicto("No hay stock suficiente para reducir esa cantidad.");
        }
        inventarioActual.setCantidad(inventarioActual.getCantidad() - dto.cantidad());
        return toResponseDto(inventarioRepository.save(inventarioActual));
    }

    public List<InventarioResponseDto> listarTodo(){
        return inventarioRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    private InventarioResponseDto toResponseDto(Inventario inventario) {
        return new InventarioResponseDto(
                inventario.getId(),
                inventario.getProducto().getNombre(),
                inventario.getSucursal().getNombre(),
                inventario.getCantidad()
        );

    }

}
