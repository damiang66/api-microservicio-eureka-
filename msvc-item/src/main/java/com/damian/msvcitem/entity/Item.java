package com.damian.msvcitem.entity;

import lombok.Data;


@Data
public class Item {
    private Producto producto;
    private Integer cantidad;

    public Item(Producto p, int i) {
        this.cantidad=i;
        this.producto=p;
    }


    public Double getTotal(){
        return producto.getPrecio()*cantidad.doubleValue();

    }
}
