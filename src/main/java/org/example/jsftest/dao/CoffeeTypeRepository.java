package org.example.jsftest.dao;

import org.example.jsftest.entity.CoffeeType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CoffeeTypeRepository
{
    public List<CoffeeType> getAllCoffeeType(){
        return new ArrayList<>();
    }

    public List<CoffeeType> getAvailableCoffeeTypes(){
        return new ArrayList<>();
    }
}
