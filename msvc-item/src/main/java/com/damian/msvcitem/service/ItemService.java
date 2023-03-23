package com.damian.msvcitem.service;

import com.damian.msvcitem.entity.Item;
import com.damian.msvcitem.entity.Producto;

import java.util.List;

public interface ItemService {
    public List<Item>findAll();
    public Item findById(Long id, Integer cantidad);
    public Producto save (Producto producto);
    public Producto editar(Producto producto, Long id);
    public void delete(Long id);


}
