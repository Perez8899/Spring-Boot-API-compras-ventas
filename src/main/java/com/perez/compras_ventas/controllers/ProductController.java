package com.perez.compras_ventas.controllers;

import com.perez.compras_ventas.common.dto.PageableRequest;
import com.perez.compras_ventas.common.dto.PageableResponse;
import com.perez.compras_ventas.dto.request.ProductAlmacenRequest;
import com.perez.compras_ventas.dto.request.ProductRequest;
import com.perez.compras_ventas.dto.response.ProductResponse;
import com.perez.compras_ventas.dto.response.ProductoFilterCriteria;
import com.perez.compras_ventas.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/paginacion")
    public PageableResponse<ProductResponse> getProductosPagination(
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) String filterValue,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String codigoBarra,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String nombreCategoria) {


        ProductoFilterCriteria criteria = ProductoFilterCriteria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .marca(marca)
                .codigoBarra(codigoBarra)
                .nombreCategoria(nombreCategoria)
                .build();
        PageableRequest<ProductoFilterCriteria> request = PageableRequest.<ProductoFilterCriteria>builder()
                .criterials(criteria)
                .filterValue(filterValue)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .sortField(sortField)
                .sortOrder(sortOrder)
                .build();
        return productService.getProductsPagination(request);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createFile(@ModelAttribute ProductRequest productRequest) {
        return ResponseEntity.<ProductResponse>ok(productService.createProducto(productRequest));
    }

    @GetMapping("/almacen/{id}")
    public ResponseEntity<List<ProductResponse>> getProductosByAlmacen(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getAllProductosByAlmacen(id));
    }

    @PostMapping("/almacen")
    public ResponseEntity<ProductResponse> createProductoAlmacen(@RequestBody ProductAlmacenRequest req) {


        return ResponseEntity.ok(productService.createProductoAlmacen(req));
    }
}
