package com.damian.msvcitem.service;

import com.damian.msvcitem.entity.Item;

import java.util.List;

public interface ItemService {
    public List<Item>findAll();
    public Item findById(Long id, Integer cantidad);

}
