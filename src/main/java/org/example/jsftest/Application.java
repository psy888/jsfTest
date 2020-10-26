package org.example.jsftest;

import org.dozer.DozerBeanMapper;
import org.example.jsftest.entity.CoffeeOrder;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.entity.OrderItem;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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

    @Bean(name = "sessionF")
    public SessionFactory getSessIonFactory()
    {
        Configuration configuration = new Configuration().configure();
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