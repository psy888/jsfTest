package org.example.jsftest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderDTO
{
    private List<OrderItemDTO> itemsDTO;
    private LocalDateTime orderDateTime;
    private String deliveryAddress;
    private String deliveryPerson;
    private double totalSum;
    private double deliveryPrice;


    public double getTotalSum(){
        totalSum =  itemsDTO.stream().collect(Collectors.summingDouble(OrderItemDTO::getTotalPrice));
        return totalSum;
    }

    public double getTotalPriceWithDelivery()
    {
        return 0;
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
