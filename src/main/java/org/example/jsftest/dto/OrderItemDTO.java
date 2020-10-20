package org.example.jsftest.dto;

import lombok.Builder;
import lombok.Data;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.entity.OrderItem;

@Data
@Builder()
public class OrderItemDTO extends OrderItem
{
    private boolean isOrdered;
    private CoffeeType coffeeType;
    private int quantity;
    private double totalPrice;
    private int discountCount;

    public double getTotalPrice()
    {
        totalPrice = coffeeType.getPrice() * quantity;
        return totalPrice;
    }
}
