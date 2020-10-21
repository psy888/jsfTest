package org.example.jsftest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.Locale;

@Component
@SessionScope
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocaleBean
{
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
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
