package org.example.jsftest.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * application properties values
 */
@Component
@Data
public class CoffeeProperties implements InitializingBean
{

    @Value("${deliveryPrice}")
    private double deliveryPrice;
    @Value("${discountedCoffeeType}")
    private String discountedCoffeeType;
    @Value("${discountedCupNum}")
    private int discountedCupNum;
    @Value("${deliveryFreeSum}")
    private double deliveryFreeSum;

    private static CoffeeProperties instance;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        instance = this;
    }

    public static CoffeeProperties getProps()
    {
        return instance;
    }
}
