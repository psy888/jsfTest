package org.example.jsftest.mBeans;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

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
    private Boolean isSuccess;
    private String string;


    public OrderDTO getCurrentOrder()
    {
        if(isNull(currentOrder))
        {
            cancelOrder();//clear fields
            currentOrder = orderService.getNewOrderDto();
        }
        return currentOrder;
    }

    public List<OrderItemDTO> getOrderedItems()
    {
        return getCurrentOrder().getItemsDTO().stream().filter(OrderItemDTO::getIsOrdered).collect(Collectors.toList());
    }

    public void confirmOrder()
    {
        try
        {
            orderService.submitOrder(currentOrder);
            isSuccess = true;
            currentOrder = null;
        }
        catch(Exception e)
        {
            isSuccess = false;
            string = e.getLocalizedMessage();
        }
    }

    public void cancelOrder()
    {
        this.isSuccess = false;
        this.string = null;
        this.currentOrder = null;
    }

}
