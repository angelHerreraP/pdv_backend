package com.pdv.pdv_backend.compra.service;

import com.pdv.pdv_backend.compra.dto.request.CreateCompraRequestDto;
import com.pdv.pdv_backend.compra.dto.request.DetallecompraRequestDto;
import com.pdv.pdv_backend.compra.dto.response.CompraResponseDto;
import com.pdv.pdv_backend.compra.dto.response.DetalleCompraResponseDto;
import com.pdv.pdv_backend.compra.entity.Compra;
import com.pdv.pdv_backend.compra.entity.DetalleCompra;
import com.pdv.pdv_backend.compra.repository.CompraRepository;
import com.pdv.pdv_backend.compra.repository.DetalleCompraRepository;
import com.pdv.pdv_backend.config.exception.CompraException;
import com.pdv.pdv_backend.inventario.dto.request.AjustarInventarioDto;
import com.pdv.pdv_backend.inventario.service.InventarioService;
import com.pdv.pdv_backend.producto.entity.Producto;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import com.pdv.pdv_backend.proveedor.entity.Proveedor;
import com.pdv.pdv_backend.proveedor.repository.ProveedorRepository;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final DetalleCompraRepository detalleCompraRepository;
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;
    private final ProveedorRepository proveedorRepository;
    private final InventarioService inventarioService;

    public CompraService(CompraRepository compraRepository, DetalleCompraRepository detalleCompraRepository, ProductoRepository productoRepository, SucursalRepository sucursalRepository, ProveedorRepository proveedorRepository, InventarioService inventarioService) {
        this.compraRepository = compraRepository;
        this.detalleCompraRepository = detalleCompraRepository;
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
        this.proveedorRepository = proveedorRepository;
        this.inventarioService = inventarioService;
    }

    public CompraResponseDto crearCompra(CreateCompraRequestDto dto) {
        Sucursal sucursal = sucursalRepository.findById(dto.sucursalId())
                .orElseThrow(() -> new CompraException("La sucursal no existe"));
        Proveedor proveedor = proveedorRepository.findById(dto.proveedorId())
                .orElseThrow(() -> new CompraException("EL proveedor al que intenta asignar esta compra no existe"));

        Compra compra = new Compra();
        compra.setProveedor(proveedor);
        compra.setSucursal(sucursal);
        compra.setFecha(LocalDateTime.now());
        compra = compraRepository.save(compra);
        List<DetalleCompraResponseDto> detallesResponse = new ArrayList<>();
        for (DetallecompraRequestDto items : dto.productos()) {
            Producto producto = productoRepository.findById(items.productoId())
                    .orElseThrow(() -> new CompraException("Producto no encontrado. "));
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setCompra(compra);
            detalleCompra.setProducto(producto);
            detalleCompra.setCostoUnitario(items.costoUnitario());
            detalleCompra.setCantidad(items.cantidad());
            detalleCompraRepository.save(detalleCompra);
            inventarioService.aumentarInventario(
                    new AjustarInventarioDto(producto.getId(), sucursal.getId(), items.cantidad())
            );
            detallesResponse.add(new DetalleCompraResponseDto(
                    producto.getId(), producto.getNombre(), items.costoUnitario(), items.cantidad()
            ));
        }
        return new CompraResponseDto(
                compra.getId(),
                proveedor.getNombre(),
                sucursal.getNombre(),
                compra.getFecha(),
                detallesResponse
        );

    }
}
