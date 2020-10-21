package org.example.jsftest.dao;

import org.example.jsftest.entity.CoffeeType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CoffeeTypeRepository
{


    public List<CoffeeType> getAvailableCoffeeTypes(){
        ArrayList<CoffeeType> list = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            CoffeeType ct = new CoffeeType();
            ct.setName("Coffee " + i);
            ct.setEnabled(true);
            ct.setPrice((1+i)*2);
            list.add(ct);
        }

        return list;
    }
}
