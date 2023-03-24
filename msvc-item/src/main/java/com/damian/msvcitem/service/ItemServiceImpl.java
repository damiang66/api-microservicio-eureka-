package com.damian.msvcitem.service;

import com.damian.msvcitem.entity.Item;
import com.damian.comun.msvccomun.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://msvc-productos/productos",Producto[].class));

        return productos.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String,String> pathVariable = new HashMap<>();
        pathVariable.put("id",id.toString());
        pathVariable.put("cantidad",cantidad.toString());
        Producto producto = restTemplate.getForObject("http://msvc-productos/productos/{id}",Producto.class,pathVariable);
        return new Item(producto,cantidad);
    }

    @Override
    public Producto save(Producto producto) {

        HttpEntity<Producto> body =new HttpEntity(producto);
        ResponseEntity<Producto> respuesta=restTemplate.exchange("http://msvc-productos/productos", HttpMethod.POST,body,Producto.class);
        Producto productoResponse = respuesta.getBody();
        return productoResponse;
    }

    @Override
    public Producto editar(Producto producto, Long id) {
        Map<String,String> pathVariable = new HashMap<>();
        pathVariable.put("id",id.toString());
        HttpEntity<Producto> body =new HttpEntity(producto);
        ResponseEntity<Producto> respuesta=restTemplate.exchange("http://msvc-productos/productos/{id}", HttpMethod.PUT,body,Producto.class,pathVariable);
        return null;
    }

    @Override
    public void delete(Long id) {
        Map<String,String> pathVariable = new HashMap<>();
        pathVariable.put("id",id.toString());
        restTemplate.delete("http://msvc-productos/productos/{id}",pathVariable);

    }
}
