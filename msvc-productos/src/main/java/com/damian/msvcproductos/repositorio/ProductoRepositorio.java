package com.damian.msvcproductos.repositorio;


import com.damian.comun.msvccomun.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
