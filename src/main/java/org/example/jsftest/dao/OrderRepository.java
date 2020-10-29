package org.example.jsftest.dao;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import org.example.jsftest.entity.CoffeeOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@Data
@AllArgsConstructor
public class OrderRepository
{

    private LocalSessionFactoryBean sessionFactory;

    public List<CoffeeOrder> findAll()
    {
        @Cleanup Session session = sessionFactory.getObject().openSession();
        List<CoffeeOrder> ordersList = session.createQuery("from CoffeeOrder").list();
        // session.close();
        return ordersList;
    }

    public void save(CoffeeOrder coffeeOrder)
    {
        @Cleanup Session session = sessionFactory.getObject().openSession();
        Transaction transaction = session.beginTransaction();

        //save ordered items
        coffeeOrder.getOrderedItems().forEach(session::save);
        //save order
        session.save(coffeeOrder);

        transaction.commit();
        // session.close();
    }

}
