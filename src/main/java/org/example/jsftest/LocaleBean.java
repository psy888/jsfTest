package org.example.jsftest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.example.jsftest.service.MessageService;
import org.example.jsftest.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.Map;

// @ManagedBean
// @SessionScoped
@Component
@SessionScope
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocaleBean
{

    @Autowired
    // @Qualifier("service")
    private MessageServiceImpl service;



    private Locale locale;

    @PostConstruct
    public void init()
    {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale()
    {
        return locale;
    }

    public String getLanguage()
    {
        return locale.getLanguage();
    }

    public void setLanguage(String language)
    {
        FacesContext.getCurrentInstance().getApplication().setMessageBundle("msg");
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        System.out.println("title = " + FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"msg").getString("title"));
        System.out.println("title = " + FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"msg").getString("title"));
        System.out.println(service.getGreetingMessage());

    }
}
