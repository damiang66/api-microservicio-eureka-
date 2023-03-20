package com.damian.msvcitem.service;

import com.damian.msvcitem.entity.Item;
import com.damian.msvcitem.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://localhost:8001/producto",Producto[].class));

        return productos.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String,String> pathVariable = new HashMap<>();
        pathVariable.put("id",id.toString());
        pathVariable.put("cantidad",cantidad.toString());
        Producto producto = restTemplate.getForObject("http://localhost:8001/producto/{id}",Producto.class,pathVariable);
        return new Item(producto,cantidad);
    }
}
