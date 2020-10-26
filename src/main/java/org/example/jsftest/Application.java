package org.example.jsftest;

import org.dozer.DozerBeanMapper;
import org.example.jsftest.entity.CoffeeOrder;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.entity.OrderItem;
import org.example.jsftest.util.SnakeCasePhysicalNamingStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties
public class Application extends SpringBootServletInitializer
{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }
    @Bean("snake")
    public PhysicalNamingStrategy getStrategy(){
        return new SnakeCasePhysicalNamingStrategy();
    }

    @Bean(name = "sessionF")
    public SessionFactory getSessIonFactory(@Qualifier("snake")PhysicalNamingStrategy strategy )
    {
        Configuration configuration = new Configuration().configure();
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