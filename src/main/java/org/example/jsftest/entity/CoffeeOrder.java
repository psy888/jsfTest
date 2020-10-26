package org.example.jsftest.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class CoffeeOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "coffeeOrder", fetch = FetchType.EAGER)
    private List<OrderItem> orderedItems;
    private Date orderDateTime;
    private String deliveryAddress;
    private String deliveryPerson;
    private double totalSum;
}
