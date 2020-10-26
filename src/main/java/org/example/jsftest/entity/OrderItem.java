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
    @ManyToOne(fetch = FetchType.EAGER)
    private CoffeeType coffeeType;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private CoffeeOrder coffeeOrder;
}
