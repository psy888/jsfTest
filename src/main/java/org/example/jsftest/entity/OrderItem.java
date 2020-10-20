package org.example.jsftest.entity;

import lombok.Data;

@Data
public class OrderItem
{
    private Long id;
    private CoffeeType coffeeType;
    private int quantity;
}
