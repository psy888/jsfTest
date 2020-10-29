package org.example.jsftest.dao;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import org.example.jsftest.entity.CoffeeType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
@AllArgsConstructor
public class CoffeeTypeRepository
{

    private LocalSessionFactoryBean sessionFactory;


    public List<CoffeeType> getAvailableCoffeeTypes()
    {
        @Cleanup Session session = sessionFactory.getObject().openSession();
        List<CoffeeType> coffeeTypes = session.createQuery("from CoffeeType where isEnabled = true").list();
        session.close();
        if(coffeeTypes.size() == 0)
        {
            addDummyData();
            coffeeTypes = getAvailableCoffeeTypes();
        }
        return coffeeTypes;
    }

    public void addDummyData()
    {
        @Cleanup Session session = sessionFactory.getObject().openSession();
        Transaction transaction = session.beginTransaction();
        for(int i = 0; i < 10; i++)
        {
            CoffeeType ct = new CoffeeType();
            ct.setName("Coffee " + i);
            ct.setEnabled(i % 2 == 0);
            ct.setPrice((1 + i) * 2);
            session.save(ct);
        }
        transaction.commit();
        // session.close();
    }


}
