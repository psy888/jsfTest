package org.example.jsftest.mBeans;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class NavigationController
{
    public String moveToOrderList(){
        return "";
    }
}
