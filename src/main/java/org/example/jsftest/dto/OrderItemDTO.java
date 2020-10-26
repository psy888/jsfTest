package org.example.jsftest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.util.SumDiscountCalculator;

@Data
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO
{
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
        if(ordered)
        {
            this.quantity += 1;
        }
        else
        {
            this.quantity = 0;
        }
    }


}
