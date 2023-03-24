package com.damian.msvcitem.entity;
import com.damian.comun.msvccomun.entity.Producto;
import lombok.Data;


@Data
public class Item {
    private Producto producto;
    private Integer cantidad;

    public Item(Producto p, int i) {
        this.cantidad=i;
        this.producto=p;
    }

    public Item() {
    }

    public Double getTotal(){
        return producto.getPrecio()*cantidad.doubleValue();

    }
}
