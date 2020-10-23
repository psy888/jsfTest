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

@Repository
@NamedQueries({
        @NamedQuery(name = "SaveOrder" , query = "")
})
@Data
@AllArgsConstructor
public class OrderRepository
{
    @Qualifier("sessionF")
    private SessionFactory sessionFactory;


    public void save(CoffeeOrder coffeeOrder)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        coffeeOrder.getOrderItems().forEach(orderItem -> session.save(orderItem.getCoffeeType()));
        coffeeOrder.getOrderItems().forEach(orderItem -> session.save(orderItem));
        session.save(coffeeOrder);
        transaction.commit();
        session.close();
    }

}
