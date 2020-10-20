package org.example.jsftest.entity;

import lombok.Data;

@Data
public class CoffeeType
{
    private Long id;
    private String name;
    private double price;
    private boolean isEnabled;
}
