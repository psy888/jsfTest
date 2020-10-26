package org.example.jsftest.mBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@Component
@RequestScope
public class ValidatorBean
{
    @Autowired
    CoffeeOrderView coffeeOrderView;


    //Validators
    public void requiredValidator(final FacesContext facesContext, final UIComponent uiComponent, final Object o) throws ValidatorException
    {
        if(o.toString().trim().isEmpty())
        {
            throw new ValidatorException(new FacesMessage(facesContext.getApplication().getResourceBundle(facesContext, "msg").getString("validatorRequiredMsg"), null)); // get msg from bundle
        }

    }

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

    public void orderFormValidation(final FacesContext facesContext, final UIComponent uiComponent, final Object o) throws ValidatorException
    {
        if(coffeeOrderView.getCurrentOrder().getOrderedItems().size() == 0)
        {
            throw new ValidatorException(new FacesMessage(facesContext.getApplication().getResourceBundle(facesContext, "msg").getString("validatorAnyMsg"), null)); //  get msg from bundle

        }

    }
}
