package org.example.jsftest.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order
{
    private Long id;
    private List<OrderItem> items;
    private LocalDateTime orderDateTime;
    private String deliveryAddress;
    private String deliveryPerson;
    private double totalSum;
}
