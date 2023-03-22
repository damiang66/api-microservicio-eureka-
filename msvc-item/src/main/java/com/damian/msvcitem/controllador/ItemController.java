package com.damian.msvcitem.controllador;

import com.damian.msvcitem.entity.Item;
import com.damian.msvcitem.entity.Producto;
import com.damian.msvcitem.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
//@RequestMapping("/item")
public class ItemController {
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private CircuitBreakerFactory cbfactory;
    @Autowired
    @Qualifier("serviceFeign")
    private ItemService service;

    public ItemController(CircuitBreakerFactory cbfactory) {
        this.cbfactory = cbfactory;
    }

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
   // return service.findById(id, cantidad);
        return cbfactory.create("items")
                .run(()->
           service.findById(id,cantidad), e -> metodoAltenativo(id,cantidad,e));

    }
    public Item metodoAltenativo(Long id, Integer cantidad,Throwable e){
     logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony alternativo");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;

    }

}
