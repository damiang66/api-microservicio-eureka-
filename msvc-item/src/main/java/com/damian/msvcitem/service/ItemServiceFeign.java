package com.damian.msvcitem.service;

import com.damian.msvcitem.client.ProductoClient;
import com.damian.msvcitem.entity.Item;
import com.damian.msvcitem.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
//@Primary indica que es la que principal a implementar
public class ItemServiceFeign implements ItemService {
    @Autowired
    private ProductoClient productoClient;
    @Override
    public List<Item> findAll() {
        return productoClient.listar().stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {

        return new Item(productoClient.ver(id).get(),cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        return productoClient.crear(producto);
    }

    @Override
    public Producto editar(Producto producto, Long id) {
        return productoClient.editar(producto,id);
    }

    @Override
    public void delete(Long id) {
    productoClient.eliminarr(id);
    }
}
