package org.example.jsftest.mBeans;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@Data
@SessionScope
public class CoffeeOrderView
{
    @Autowired
    private OrderService orderService;
    private OrderDTO currentOrder;
    private String errMsg;



    public OrderDTO getCurrentOrder()
    {
        if(isNull(currentOrder))
        {
            currentOrder = orderService.getNewOrderDto();
        }
        return currentOrder;
    }

    public List<OrderItemDTO> getOrderedItems()
    {
        return getCurrentOrder().getOrderedItems();
    }

    public void confirmOrder()
    {
        errMsg = null;
        try
        {
            orderService.submitOrder(currentOrder);
            currentOrder = null;
        }
        catch(Exception e)
        {
            errMsg = e.getLocalizedMessage();
        }
    }

    public void cancelOrder()
    {
        this.errMsg = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"msg").getString("cancelByUser");
        this.currentOrder = null;
    }

    public void clearErrors(){
        errMsg = null;
    }

    public List<OrderDTO> getOrdersList()
    {
        return orderService.findAllOrders();
    }

}
