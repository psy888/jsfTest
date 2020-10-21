package org.example.jsftest.service;

import lombok.Data;
import org.example.jsftest.dao.CoffeeTypeRepository;
import org.example.jsftest.entity.CoffeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CoffeeTypeService
{
    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    public List<CoffeeType> getAllAvailableCoffeeTypes(){
        return coffeeTypeRepository.getAvailableCoffeeTypes();
    }
}
