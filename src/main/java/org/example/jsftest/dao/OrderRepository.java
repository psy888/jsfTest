package org.example.jsftest.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.jsftest.entity.CoffeeOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@Data
@AllArgsConstructor
public class OrderRepository
{

    private SessionFactory sessionFactory;

    public List<CoffeeOrder> findAll(){
        Session session = sessionFactory.openSession();
        List<CoffeeOrder> ordersList = session.createQuery("from CoffeeOrder").list();
        session.close();
        return ordersList;
    }

    public void save(CoffeeOrder coffeeOrder)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        //save ordered items
        coffeeOrder.getOrderedItems().forEach(session::save);
        //save order
        session.save(coffeeOrder);

        transaction.commit();
        session.close();
    }

}
