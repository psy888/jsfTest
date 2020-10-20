package org.example.jsftest.mBeans;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@Component
@SessionScope
@Data
public class OrderView
{
    private OrderService orderService;
    private String deliveryAddress;
    private String deliveryPerson;


    public void updateOrder()
    {
        OrderDTO order = orderService.getCurrentOrderDto();
        order.setDeliveryAddress(deliveryAddress);
        order.setDeliveryPerson(deliveryPerson);
    }


    public OrderDTO getCurrentOrder(){
        return orderService.getCurrentOrderDto();
    }

    public void confirmOrder()
    {
        // orderService.confirmCurrentOrder();
    }


    //Validators
    public void requiredValidator(final FacesContext facesContext, final UIComponent uiComponent, final Object o) throws ValidatorException
    {
        if(o.toString().isEmpty())
        {
            throw new ValidatorException(new FacesMessage(facesContext.getApplication().getResourceBundle(facesContext, "org.example.jsftest.msgs").getString("validatorRequiredMsg"), null)); // get msg from bundle
        }

    }
}
