package org.example.jsftest.mBeans;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.SessionScoped;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@Data
@SessionScope
public class CoffeeOrderView
{
    @Autowired
    private OrderService orderService;
    private OrderDTO currentOrder;


    public OrderDTO getCurrentOrder()
    {
        if(isNull(currentOrder))
        {
            currentOrder = orderService.getCurrentOrderDto();
        }
        return currentOrder;
    }

    public List<OrderItemDTO> getOrderedItems(){
        return currentOrder.getItemsDTO().stream().filter(OrderItemDTO::getIsOrdered).collect(Collectors.toList());
    }

    public void confirmOrder()
    {
        orderService.confirmCurrentOrder(); //todo popup msg if success
    }
}
