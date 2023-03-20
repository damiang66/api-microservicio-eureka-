package com.damian.msvcitem.controllador;

import com.damian.msvcitem.entity.Item;
import com.damian.msvcitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    @Qualifier("serviceFeign")
    private ItemService service;
    @GetMapping
    public ResponseEntity<?>listar(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
    return service.findById(id, cantidad);
    }

}
