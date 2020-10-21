package org.example.jsftest.mBeans;

import lombok.Data;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.List;

@Component
@Data
@SessionScope
public class CoffeeListView
{
    @Autowired
    private OrderService orderService;



    public String doOrder()
    {
        //toDo navigate to delivery page
        return "delivery";
    }

    public List<OrderItemDTO> getOrderItems()
    {
        return orderService.getCurrentOrderDto().getItemsDTO();
    }


    //Validators
    public void quantityValidator(final FacesContext facesContext, final UIComponent uiComponent, final Object o) throws ValidatorException
    {
        try
        {
            int quantity = Integer.parseInt(o.toString());

            if(quantity < 0)
            {
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e)
        {
            throw new ValidatorException(new FacesMessage(facesContext.getApplication().getResourceBundle(facesContext, "msg").getString("validatorQuantityMsg"), null)); //  get msg from bundle
        }
    }
}
