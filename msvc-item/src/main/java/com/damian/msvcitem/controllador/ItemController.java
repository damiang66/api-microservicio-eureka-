package com.damian.msvcitem.controllador;

import com.damian.msvcitem.entity.Item;
import com.damian.msvcitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
//@RequestMapping("/item")
public class ItemController {
    @Autowired
    @Qualifier("serviceFeign")
    private ItemService service;
    @GetMapping
    public ResponseEntity<?>listar(@RequestParam(name="nombre", required = false)String nombre, @RequestHeader(name = "token-request",required = false) String token){
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("lista", service.findAll());
        respuesta.put("parametro", nombre);
        respuesta.put("RequestHeader", token);

        return ResponseEntity.ok().body(respuesta);
    }
    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
    return service.findById(id, cantidad);
    }

}
