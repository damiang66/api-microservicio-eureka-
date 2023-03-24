package com.damian.msvcitem.controllador;

import com.damian.msvcitem.entity.Item;
import com.damian.comun.msvccomun.entity.Producto;
import com.damian.msvcitem.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@RestController
//@RequestMapping("/item")
public class ItemController {
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);


    private Environment env;

    private CircuitBreakerFactory cbfactory;
    @Autowired
    @Qualifier("serviceFeign")
   // @Qualifier("serviceRestTemplate")
    private ItemService service;
    @Value("${configuracion.texto}")
    private String texto;
    public ItemController(Environment env, CircuitBreakerFactory cbfactory) {
        this.env = env;
        this.cbfactory = cbfactory;
    }
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id){
        return service.editar(producto,id);
    }
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?>obtenerConfig(@Value("${server.port}") String puerto){
        Map<String,String> mapa = new HashMap<>();
        mapa.put("texto",texto);
        mapa.put("puerto",puerto);
       logger.info(env.getProperty("configuracion.autor.nombre"));
        if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")){
            mapa.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            mapa.put("autor.email", env.getProperty("configuracion.autor.email"));
        }
        return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?>listar(@RequestParam(name="nombre", required = false)String nombre, @RequestHeader(name = "token-request",required = false) String token){
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("lista", service.findAll());
        respuesta.put("parametro", nombre);
        respuesta.put("RequestHeader", token);
        logger.info(texto);

        return ResponseEntity.ok().body(respuesta);
    }
    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
   // return service.findById(id, cantidad);
        return cbfactory.create("items")
                .run(()->
           service.findById(id,cantidad), e -> metodoAltenativo(id,cantidad,e));

    }
    // cortocircuito
    @CircuitBreaker(name="items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/error/{id}/cantidad/{cantidad}")
    public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad){
        // return service.findById(id, cantidad);
        return   service.findById(id,cantidad);

    }
    // tiempo de espera
    @TimeLimiter(name="items", fallbackMethod = "metodoAlternativo2")
    @GetMapping("/error1/{id}/cantidad/{cantidad}")
    public CompletableFuture<Item>  detalle3(@PathVariable Long id, @PathVariable Integer cantidad){
        // return service.findById(id, cantidad);
        return CompletableFuture.supplyAsync(()->service.findById(id,cantidad));

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

    public CompletableFuture<Item> metodoAltenativo2(Long id, Integer cantidad,Throwable e){
        logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony alternativo");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return CompletableFuture.supplyAsync(()->item);

    }

}
