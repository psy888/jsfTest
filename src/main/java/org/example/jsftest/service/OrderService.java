package org.example.jsftest.service;

import lombok.Data;
import org.example.jsftest.dao.OrderRepository;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class OrderService
{
    private OrderRepository orderRepository;
    private CoffeeTypeService coffeeTypeService;
    //temporary
    private OrderDTO currentOrderDto;


    public List<OrderItemDTO> getAvailableOrderItemDTOs()
    {
        return getNewOrderItems();
    }

    /**
     * Get New OrderItemsDTO based on Available (marked isEnabled=true) CoffeeTypes
     *
     * @return list OrderItemDTO
     */
    private List<OrderItemDTO> getNewOrderItems()
    {
        return coffeeTypeService.getAllAvailableCoffeeTypes()
                .stream()
                .map(coffeeType -> OrderItemDTO.builder().coffeeType(coffeeType).build())
                .collect(Collectors.toList());
    }

    public void makeOrder(final List<OrderItemDTO> orderItems)
    {
        currentOrderDto = OrderDTO.builder()
                .itemsDTO(orderItems)
                .orderDateTime(LocalDateTime.now())
                .build();
    }

}
