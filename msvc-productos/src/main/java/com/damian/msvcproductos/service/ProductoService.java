package com.damian.msvcproductos.service;

import com.damian.msvcproductos.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto>findAll();
    public Optional<Producto>findById(Long id);
    public Producto save(Producto producto);
    public void delete(Long id);
}
