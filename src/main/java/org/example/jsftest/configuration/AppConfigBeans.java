package org.example.jsftest.configuration;

import org.dozer.DozerBeanMapper;
import org.example.jsftest.entity.CoffeeOrder;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.entity.OrderItem;
import org.example.jsftest.util.SnakeCasePhysicalNamingStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigBeans
{
    @Bean("snake")
    public PhysicalNamingStrategy getStrategy()
    {
        return new SnakeCasePhysicalNamingStrategy();
    }

    @Bean(name = "sessionF")
    public SessionFactory getSessIonFactory(@Qualifier("snake") PhysicalNamingStrategy strategy)
    {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
        configuration.setPhysicalNamingStrategy(strategy);
        configuration.addAnnotatedClass(CoffeeType.class);
        configuration.addAnnotatedClass(OrderItem.class);
        configuration.addAnnotatedClass(CoffeeOrder.class);
        return configuration.buildSessionFactory();
    }

    @Bean
    public DozerBeanMapper getMapper()
    {
        return new DozerBeanMapper();
    }

}
