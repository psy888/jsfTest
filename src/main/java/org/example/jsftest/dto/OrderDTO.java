package org.example.jsftest.dto;

import lombok.Builder;
import lombok.Data;
import org.example.jsftest.util.SumDiscountCalculator;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderDTO
{
    private List<OrderItemDTO> orderItems;
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

    public double getTotalSum()
    {
        this.totalSum = SumDiscountCalculator.getTotal(this, true);
        return totalSum;
    }

    public double getDeliveryPrice()
    {
        deliveryPrice = SumDiscountCalculator.getDeliveryPrice(this);
        return deliveryPrice;
    }


    public List<OrderItemDTO> getOrderedItemsDTO()
    {
        List<OrderItemDTO> list = availableItems.stream().filter(OrderItemDTO::getIsOrdered).collect(Collectors.toList());
        return list;
        // return orderItems;
    }
    // /**
    //  * Map OrderItemDTO into OrderItem Entity
    //  * @param items dto
    //  */
    // public void setItemsDTO(final List<OrderItemDTO> items){
    //     List<OrderItem> entityList = items.stream()
    //             .filter(OrderItemDTO::isOrdered)
    //             .map(orderItemDTO -> {
    //         OrderItem oi = new OrderItem();
    //         oi.setCoffeeType(orderItemDTO.getCoffeeType());
    //         oi.setQuantity(orderItemDTO.getQuantity());
    //         return oi;
    //     }).collect(Collectors.toList());
    //     super.setItems(entityList);
    //
    // }
}
