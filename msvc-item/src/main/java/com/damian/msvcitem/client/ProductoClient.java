package com.damian.msvcitem.client;


import com.damian.comun.msvccomun.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "msvc-productos")
public interface ProductoClient {
    @GetMapping
    public List<Producto> listar();
    @GetMapping("/{id}")
    public Optional<Producto> ver(@PathVariable Long id);
    @PostMapping
    public Producto crear(@RequestBody Producto producto);
    @PutMapping("/{id}")
    public Producto editar(@RequestBody Producto producto,@PathVariable Long id );
    @DeleteMapping("/{id}")
    public Producto eliminarr(@PathVariable Long id );
}
