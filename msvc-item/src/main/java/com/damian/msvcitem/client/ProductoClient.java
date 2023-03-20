package com.damian.msvcitem.client;

import com.damian.msvcitem.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "msvc-productos")
public interface ProductoClient {
    @GetMapping
    public List<Producto> listar();
    @GetMapping("/{id}")
    public Optional<Producto> ver(@PathVariable Long id);
}
