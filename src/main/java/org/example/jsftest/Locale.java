package org.example.jsftest;

import lombok.Data;
import org.example.jsftest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.HashMap;

@ManagedBean(name = "locale", eager = true)
@SessionScoped
@Data
public class Locale implements Serializable
{
    private static final long serialVersionUID = 1L;


    private java.util.Locale curLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private String locale = "en";
    private HashMap<String, String> locales = new HashMap<>();

    {
        locales.put("Русский", "ru");
        locales.put("English", "en");
    }

    public String getCurrentLocale(){
        return curLocale.toString();
    }

    public void submit()
    {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new java.util.Locale(this.locale));
        curLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public void setLocale(String locale){
        this.locale = locale;
        submit();
    }
    public void countryLocaleCodeChanged(ValueChangeEvent e)
    {
        String newLocale = e.getNewValue().toString();

        System.out.println(locales.get(newLocale));
        locale = locales.get(newLocale);
        submit();
    }
}

