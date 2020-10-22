package org.example.jsftest.util;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Value;

import java.util.stream.Collectors;

/**
 * Util class to calculate sum of order with discounts and without it
 */
@Data
public class SumDiscountCalculator
{


    /**
     * get Total sum of all ordered items with or without delivery
     *
     * @param order
     * @param withDeliveryTax get sum with delivery
     *
     * @return total sum of order with all discounts
     */
    public static double getTotal(OrderDTO order, boolean withDeliveryTax)
    {
        double sum = getItemsCostSum(order);

        if(withDeliveryTax)
        {
            return getDeliveryPrice(sum) + sum;
        }
        else
        {
            return sum;
        }
    }



    /**
     * if discounted coffee type subtract each discounted cup from total quantity of cups and multiply price
     * else return count * price
     *
     * @param orderItem
     *
     * @return order item sub total
     */
    public static double getItemCostSum(OrderItemDTO orderItem)
    {
        if(orderItem.getCoffeeType().getName().contentEquals(CoffeeProperties.getProps().getDiscountedCoffeeType()))
        {
            return (orderItem.getQuantity() - Math.floor(orderItem.getQuantity() / CoffeeProperties.getProps().getDiscountedCupNum())) * orderItem.getCoffeeType().getPrice();
        }
        else
        {
            return orderItem.getQuantity() * orderItem.getCoffeeType().getPrice();
        }
    }

    /**
     * get delivery price (if order items sum price reached deliveryFreeSum delivery price is 0)
     * @param order
     * @return delivery price
     */
    public static double getDeliveryPrice(OrderDTO order)
    {
        double orderSum = getItemsCostSum(order);
        return (orderSum > CoffeeProperties.getProps().getDeliveryFreeSum()) ? 0 : CoffeeProperties.getProps().getDeliveryPrice();
    }

    private static double getDeliveryPrice(double orderSum)
    {
        return (orderSum > CoffeeProperties.getProps().getDeliveryFreeSum()) ? 0 : CoffeeProperties.getProps().getDeliveryPrice();
    }

    /**
     * get Total sum of all ordered items
     *
     * @param order
     *
     * @return
     */
    private static double getItemsCostSum(OrderDTO order)
    {
        return order.getItemsDTO().stream().filter(OrderItemDTO::getIsOrdered).collect(Collectors.summingDouble(SumDiscountCalculator::getItemCostSum));
    }

}
