package com.damian.msvcproductos.controllers;

import com.damian.msvcproductos.entity.Producto;
import com.damian.msvcproductos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
//@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Producto> r = service.findById(id);
        if (r.isPresent()) {
            return ResponseEntity.ok().body(r.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?>save (@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>editar(@RequestBody Producto producto, @PathVariable Long id) throws InterruptedException {
        if(id.equals(10L)){
            throw new IllegalStateException("producto 10 provoco un error");
        }
        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }
        Optional<Producto> r = service.findById(id);
        if (r.isPresent()) {
            Producto productoDb= r.get();
            productoDb.setNombre(producto.getNombre());
            productoDb.setPrecio(producto.getPrecio());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoDb));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        Optional<Producto> r = service.findById(id);
        if (r.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
