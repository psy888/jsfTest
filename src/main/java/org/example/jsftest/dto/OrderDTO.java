package org.example.jsftest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsftest.util.SumDiscountCalculator;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO
{
    private List<OrderItemDTO> orderedItems;
    private List<OrderItemDTO> availableItems;
    private Date orderDateTime;
    private String deliveryAddress;
    private String deliveryPerson;
    private double deliveryPrice;
    private double totalSum;




    /**
     * get total sum of order without delivery
     * @return
     */
    public double getTotalSumWODelivery(){
        return SumDiscountCalculator.getTotal(this,false);
    }


    /**
     *
     * @return
     */
    public double getTotalSum()
    {
        this.totalSum = SumDiscountCalculator.getTotal(this, true);
        return totalSum;
    }

    /**
     * Get delivery price dependent of total sum of order
     * @return
     */
    public double getDeliveryPrice()
    {
        deliveryPrice = SumDiscountCalculator.getDeliveryPrice(this);
        return deliveryPrice;
    }

    /**
     * @return list of ordered items
     */
    public List<OrderItemDTO> getOrderedItems()
    {
        List<OrderItemDTO> list = availableItems.stream().filter(OrderItemDTO::getIsOrdered).collect(Collectors.toList());
        return list;
    }
}
