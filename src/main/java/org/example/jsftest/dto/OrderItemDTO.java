package org.example.jsftest.dto;

import lombok.Builder;
import lombok.Data;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.entity.OrderItem;
import org.example.jsftest.util.SumDiscountCalculator;

@Data
@Builder()
public class OrderItemDTO extends OrderItem
{
    // private boolean isOrdered;
    private CoffeeType coffeeType;
    private int quantity;
    private double totalPrice;

    public double getTotalPrice()
    {
        totalPrice = SumDiscountCalculator.getItemCostSum(this);
        return totalPrice;
    }

    //for JSF standard getter
    public boolean getIsOrdered()
    {
        return this.quantity > 0;
    }

    public void setIsOrdered(final boolean ordered)
    {
        if(ordered){
            this.quantity += 1;
        }else {
            this.quantity = 0;
        }
    }




}
