package com.damian.msvcproductos.service;

import com.damian.msvcproductos.entity.Producto;
import com.damian.msvcproductos.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepositorio repositorio;
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return repositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
    repositorio.deleteById(id);
    }
}
