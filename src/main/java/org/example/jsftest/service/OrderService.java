package org.example.jsftest.service;

import lombok.Data;
import org.example.jsftest.dao.OrderRepository;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Data
// @RequestScope
@SessionScope
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CoffeeTypeService coffeeTypeService;
    // //temporary
    // private OrderDTO currentOrderDto;


    /**
     * Get New OrderItemsDTO based on Available (marked isEnabled=true) CoffeeTypes
     *
     * @return list OrderItemDTO
     */
    private List<OrderItemDTO> getNewOrderItems()
    {
        return coffeeTypeService.getAllAvailableCoffeeTypes().stream().map(coffeeType -> OrderItemDTO.builder().coffeeType(coffeeType).build()).collect(Collectors.toList());
    }

    public OrderDTO getNewOrderDto()
    {
        // if(isNull(currentOrderDto))
        // {
        //     currentOrderDto = OrderDTO.builder().itemsDTO(getNewOrderItems()).orderDateTime(LocalDateTime.now()).build();
        // }
        // return currentOrderDto;
        return OrderDTO.builder().itemsDTO(getNewOrderItems()).orderDateTime(LocalDateTime.now()).build();
    }

    public void submitOrder(final OrderDTO currentOrder) throws Exception
    {

        throw new Exception("DB Error!");
        //todo map current order to entity and save it to db
        // System.out.println("ORDER SAVED");
        // currentOrderDto = null;
    }
}
