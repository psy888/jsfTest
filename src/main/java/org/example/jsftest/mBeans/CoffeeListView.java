package org.example.jsftest.mBeans;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@SessionScope
@Data
public class CoffeeListView
{
    // private CoffeeService coffeeService;
    private OrderService orderService;

    private List<OrderItemDTO> orderItems;


    public void makeNewOrder(){
        orderService.makeOrder(orderItems);
    }

    public List<OrderItemDTO> getOrderItems(){
        orderItems = orderService.getAvailableOrderItemDTOs();
        return orderItems;
    }

    //Validators
    public void quantityValidator(final FacesContext facesContext,
                                  final UIComponent uiComponent,
                                  final Object o) throws ValidatorException
    {
        try
        {
            int quantity = Integer.parseInt(o.toString());

            if(quantity < 0)
            {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            throw new ValidatorException(new FacesMessage(
                    facesContext.getApplication().getResourceBundle(facesContext,"org.example.jsftest.msgs").getString("validatorQuantityMsg")
                    , null)); //  get msg from bundle
        }
    }
}
