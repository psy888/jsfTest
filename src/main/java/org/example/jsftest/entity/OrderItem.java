package org.example.jsftest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CoffeeType coffeeType;
    private int quantity;
    @ManyToOne
    private CoffeeOrder coffeeOrder;
}
