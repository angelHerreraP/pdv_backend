package com.pdv.pdv_backend.autorizations.service;

import com.pdv.pdv_backend.auth.entity.Usuario;
import com.pdv.pdv_backend.auth.repository.UsuarioRepository;
import com.pdv.pdv_backend.autorizations.constats.AccionAutorizada;
import com.pdv.pdv_backend.autorizations.dto.request.CrearCodigoDescuentoRequestDto;
import com.pdv.pdv_backend.autorizations.dto.request.CrearCodigoTraspasoRequestDto;
import com.pdv.pdv_backend.autorizations.dto.response.CodigoAutorizacionResponseDto;
import com.pdv.pdv_backend.autorizations.entity.CodigoAutorizacion;
import com.pdv.pdv_backend.autorizations.repository.CodigoAutorizacionRepository;
import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import com.pdv.pdv_backend.sucursal.repository.SucursalRepository;
import com.pdv.pdv_backend.venta.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CodigoAutorizacionService {
    private final CodigoAutorizacionRepository codigoAutorizacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    public CodigoAutorizacionService(CodigoAutorizacionRepository codigoAutorizacionRepository, UsuarioRepository usuarioRepository, SucursalRepository sucursalRepository, ProductoRepository productoRepository, VentaRepository ventaRepository) {
        this.codigoAutorizacionRepository = codigoAutorizacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Value("${autorizacion.vigencia-minutos:10}")
    private long vigenciaMinutos;

    private static final String CARACTERES = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private final SecureRandom random = new SecureRandom();



    @Transactional
    public CodigoAutorizacionResponseDto crearCodigoDescuento(CrearCodigoDescuentoRequestDto dto, Long adminId){
        if(dto.esPorcentaje() && dto.valor().compareTo(BigDecimal.valueOf(100) ) > 0){
            throw ApiException.conflicto("El porcentaje no puede ser mayor a 100");
        }
        return guardar(AccionAutorizada.DESCUENTO, adminId, Map
                .of("esPorcentaje", dto.esPorcentaje(), "valor", dto.valor()));

    }


    @Transactional
    public CodigoAutorizacionResponseDto crearCodigoTraspaso(CrearCodigoTraspasoRequestDto dto, Long adminId){
        if(dto.origenId().equals(dto.destinoId())){
            throw ApiException.conflicto("No puede traspasarse entre la misma sucursal");
        }
        sucursalRepository.findById(dto.origenId())
                .orElseThrow(() -> ApiException.noEncontrado("La sucursal de origen no existe"));
        sucursalRepository.findById(dto.destinoId())
                .orElseThrow(() -> ApiException.noEncontrado("La sucursal de destino no existe"));
        productoRepository.findById(dto.productoId())
                .orElseThrow(() -> ApiException.noEncontrado("El producto no existe."));

        return guardar(AccionAutorizada.TRASPASO, adminId,
                Map.of("origenId", dto.origenId(), "destinoId", dto.destinoId(), "productoId", dto.productoId(), "cantidad", dto.cantidad()));
    }

/*
    @Transactional
    public CodigoAutorizacionResponseDto crearCambioVenta(CrearCodigoEditarVentaRequestDto dto, Long adminId){
        Venta venta = ventaRepository.findById(dto.ventaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("La venta a modificar no existe"));

        if(codigoAutorizacionRepository.existsVigentePara(AccionAutorizada.CAMBIAR_VENTA.name(), venta.getId(), LocalDateTime.now())){
            throw new RecursoNoEncontradoException("La venta a modificar no es")
        }
    }
*/
    private CodigoAutorizacionResponseDto guardar(AccionAutorizada accion, Long adminId, Map<String, Object> parametros){
        Usuario admin = usuarioRepository.findById(adminId)
                .orElseThrow(() -> ApiException.noEncontrado("No se encontro el usuario"));
        LocalDateTime ahora = LocalDateTime.now();
        CodigoAutorizacion c = new CodigoAutorizacion();
        c.setCodigo(generarCodigoUnico());
        c.setAccion(accion);
        c.setParametros(parametros);
        c.setCreadoPor(admin);
        c.setCreadoEn(ahora);
        c.setExpiraEn(ahora.plusMinutes(vigenciaMinutos));
        c.setUsado(false);
        return toResponseDto(c);
    }

    private CodigoAutorizacionResponseDto toResponseDto(CodigoAutorizacion codigoAutorizacion){
        return new CodigoAutorizacionResponseDto(
                codigoAutorizacion.getId(),
                codigoAutorizacion.getCodigo(),
                codigoAutorizacion.getAccion(),
                codigoAutorizacion.getParametros(),
                codigoAutorizacion.getCreadoEn(),
                codigoAutorizacion.getExpiraEn()
        );
    }

    private String generarCodigoUnico(){
        String codigo;
        do {
            StringBuilder sb = new StringBuilder(8);
            for(int i = 0; i < 8; i++){
                sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
            }
            codigo = sb.toString();
        } while (codigoAutorizacionRepository.findByCodigo(codigo).isPresent());
        return codigo;
    }
}
