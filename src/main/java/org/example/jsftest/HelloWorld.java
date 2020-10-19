package org.example.jsftest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Managed bean for home.xhtml page
 */
@ManagedBean(name = "helloWorld",eager = true)
@ViewScoped
public class HelloWorld implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<String> inputDataList;
    private String msg;

    public HelloWorld()
    {
        this.inputDataList = new ArrayList<>();
    }


    public List<String> addToList(){
        if(nonNull(msg)&&!msg.isEmpty())
        {
            inputDataList.add(msg);
        }
        return inputDataList;
    }

    public void setMsg(final String msg)
    {
        this.msg = msg;
        addToList();
    }

    public String getMsg()
    {
        return msg;
    }

    public List<String> getInputDataList()
    {
        return inputDataList;
    }

    public void setInputDataList(final List<String> inputDataList)
    {
        this.inputDataList = inputDataList;
    }

    public String getMessage(){
        return "Hello WTF ?@%$#%#^#";
    }


}
