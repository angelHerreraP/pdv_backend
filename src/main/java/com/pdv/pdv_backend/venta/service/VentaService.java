package com.pdv.pdv_backend.venta.service;

import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.inventario.dto.request.AjustarInventarioDto;
import com.pdv.pdv_backend.inventario.service.InventarioService;
import com.pdv.pdv_backend.producto.entity.Producto;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import com.pdv.pdv_backend.vendedores.entity.TurnoVendedor;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.vendedores.repository.TurnoVendedorRepository;
import com.pdv.pdv_backend.vendedores.repository.VendedorRepository;
import com.pdv.pdv_backend.venta.constants.EstadoVenta;
import com.pdv.pdv_backend.venta.constants.MetodoPago;
import com.pdv.pdv_backend.venta.dto.request.CrearVentaRequestDto;
import com.pdv.pdv_backend.venta.dto.request.DetalleVentaRequestDto;
import com.pdv.pdv_backend.venta.dto.response.DetalleVentaResponseDto;
import com.pdv.pdv_backend.venta.dto.response.VentaResponseDto;
import com.pdv.pdv_backend.venta.entity.DetalleVenta;
import com.pdv.pdv_backend.venta.entity.Venta;
import com.pdv.pdv_backend.venta.repository.DetalleVentaRepository;
import com.pdv.pdv_backend.venta.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final InventarioService inventarioService;
    private final SucursalRepository sucursalRepository;
    private final VendedorRepository vendedorRepository;
    private final ProductoRepository productoRepository;
    private final TurnoVendedorRepository turnoVendedorRepository;

    public VentaService(VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository, SucursalRepository sucursalRepository, VendedorRepository vendedorRepository, ProductoRepository productoRepository, TurnoVendedorRepository turnoVendedorRepository, InventarioService inventarioService) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.inventarioService = inventarioService;
        this.sucursalRepository = sucursalRepository;
        this.vendedorRepository = vendedorRepository;
        this.productoRepository = productoRepository;
        this.turnoVendedorRepository = turnoVendedorRepository;
    }

    @Transactional
    public VentaResponseDto crearVenta(CrearVentaRequestDto dto){
        Sucursal sucursal = sucursalRepository.findById(dto.sucursalId())
                .orElseThrow(() -> ApiException.noEncontrado("La sucursal no se encuentra"));
        Vendedor vendedor = vendedorRepository.findById(dto.vendedorId())
                .orElseThrow(() -> ApiException.noEncontrado("EL vendedor no existe o tiene un problema."));

        TurnoVendedor turnoVendedor = turnoVendedorRepository.findByVendedorIdAndHoraSalidaIsNull(vendedor.getId())
                .orElseThrow(() -> ApiException.conflicto("EL vendedor no tiene turno activo"));
        if(!turnoVendedor.getSucursal().getId().equals(dto.sucursalId())){
            throw ApiException.conflicto("El vendedor está activo en otra sucursal");
        }
        Venta venta = new Venta();
        venta.setSucursal(sucursal);
        venta.setVendedor(vendedor);
        venta.setMetodoPago(MetodoPago.EFECTIVO);
        venta.setEstado(EstadoVenta.CERRADA);
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(BigDecimal.ZERO);
        venta = ventaRepository.save(venta);


        //sumar totales
        BigDecimal total = BigDecimal.ZERO;
        List<DetalleVentaResponseDto> lineas = new ArrayList<>();

        for(DetalleVentaRequestDto item : dto.productos()){
            Producto producto = productoRepository.findById(item.productoId())
                    .orElseThrow(() -> ApiException.noEncontrado("Product no encontrado"));

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(item.cantidad());
            detalleVenta.setPrecioUnitario(producto.getPrecioPublico());
            detalleVentaRepository.save(detalleVenta);

            BigDecimal subtotal = producto.getPrecioPublico()
                    .multiply(BigDecimal.valueOf(item.cantidad()));
            total = total.add(subtotal);

            inventarioService.reducirInventario(
                    new AjustarInventarioDto(producto.getId(), sucursal.getId(), item.cantidad())
            );
            lineas.add(new DetalleVentaResponseDto(
                    producto.getId(), producto.getNombre(),
                    item.cantidad(), producto.getPrecioPublico(), subtotal
            ));
        }
        venta.setTotal(total);
        venta = ventaRepository.save(venta);
        return new VentaResponseDto(
                venta.getId(), vendedor.getNombre(), sucursal.getNombre(), "EFECTIVO", null, total, BigDecimal.ZERO, total, venta.getFecha(), lineas
        );

    }
}
