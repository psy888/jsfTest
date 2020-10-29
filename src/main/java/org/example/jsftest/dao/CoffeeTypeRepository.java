package org.example.jsftest.dao;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import org.example.jsftest.entity.CoffeeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.query.Query;
import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
@AllArgsConstructor
public class CoffeeTypeRepository
{

    private SessionFactory sessionFactory;


    public List<CoffeeType> getAvailableCoffeeTypes()
    {
        @Cleanup Session session = sessionFactory.openSession();
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
        @Cleanup Session session = sessionFactory.openSession();
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
