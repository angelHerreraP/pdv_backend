package com.pdv.pdv_backend.producto.service;

import com.pdv.pdv_backend.categoria.entity.Categoria;
import com.pdv.pdv_backend.categoria.repository.CategoriaRepository;
import com.pdv.pdv_backend.config.exception.ApiException;
import com.pdv.pdv_backend.marca.entity.Marca;
import com.pdv.pdv_backend.marca.repository.MarcaRepository;
import com.pdv.pdv_backend.producto.dto.request.CreateProductRequestDto;
import com.pdv.pdv_backend.producto.dto.response.ProductoResponse;
import com.pdv.pdv_backend.producto.entity.Producto;
import com.pdv.pdv_backend.producto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class ProductoService {
    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository pR, MarcaRepository mR, CategoriaRepository cR){
        this.productoRepository = pR;
        this.marcaRepository = mR;
        this.categoriaRepository = cR;
    }

    public ProductoResponse crearProducto(CreateProductRequestDto dto){
        if(dto.precioPublico().compareTo(BigDecimal.ZERO) <= 0){
            throw ApiException.invalido("EL precio debe ser mayor a cero.");
        }
        Marca marca = marcaRepository.findById(dto.marcaId())
                .orElseThrow(() -> ApiException.noEncontrado("No conozco la marca."));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> ApiException.noEncontrado("Categoria no encontrada."));

        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setMarca(marca);
        producto.setCategoria(categoria);
        producto.setPrecioPublico(dto.precioPublico());
        producto.setRequiereSerie(dto.requiereSerie() != null ? dto.requiereSerie() : false);
        producto.setCodigoBarras(dto.codigoBarras());
        producto.setCreadoEn(LocalDateTime.now());

        Producto guardado = productoRepository.save(producto);
        return toResponseDTO(guardado);
    }

    public List<ProductoResponse> listarProductos(){
        return productoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private ProductoResponse toResponseDTO(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getMarca().getNombre(),
                producto.getCategoria().getNombre(),
                producto.getPrecioPublico(),
                producto.getRequiereSerie(),
                producto.getCodigoBarras()
        );
    }
}
