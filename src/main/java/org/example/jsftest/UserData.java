package org.example.jsftest;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import static java.util.Objects.isNull;

@ManagedBean(name = "userData", eager = true)
@ViewScoped
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Date startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWelcomeMessage() {
        return "Hello " + name;
    }

    public Date getDate(){
     // if(isNull(startDate)){
     //     startDate = new Date();
     // }
     return new Date();
    }
}
