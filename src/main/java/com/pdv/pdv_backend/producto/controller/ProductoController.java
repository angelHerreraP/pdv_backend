package com.pdv.pdv_backend.producto.controller;

import com.pdv.pdv_backend.producto.dto.request.CreateProductRequestDto;
import com.pdv.pdv_backend.producto.dto.response.ProductoResponse;
import com.pdv.pdv_backend.producto.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponse crear(@RequestBody CreateProductRequestDto dto){
        return productoService.crearProducto(dto);
    }

    @GetMapping
    public List<ProductoResponse> listar(){
        return productoService.listarProductos();
    }
}
