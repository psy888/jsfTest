package org.example.jsftest.configuration;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.example.jsftest.entity.CoffeeOrder;
import org.example.jsftest.entity.CoffeeType;
import org.example.jsftest.entity.OrderItem;
import org.example.jsftest.util.SnakeCasePhysicalNamingStrategy;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@Data
public class AppConfigBeans
{
    @Resource(lookup = "java:/testDB")
    DataSource dataSource;

    @Bean("snake")
    public PhysicalNamingStrategy getStrategy()
    {
        return new SnakeCasePhysicalNamingStrategy();
    }

    @Bean
    public org.hibernate.cfg.Configuration getHibernateConf(@Qualifier("snake") PhysicalNamingStrategy strategy){
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
        configuration.setPhysicalNamingStrategy(strategy);
        configuration.addAnnotatedClass(CoffeeType.class);
        configuration.addAnnotatedClass(OrderItem.class);
        configuration.addAnnotatedClass(CoffeeOrder.class);
        return configuration;
    }
    @Bean(name = "sessionF")
    public SessionFactory getSessIonFactory(org.hibernate.cfg.Configuration configuration, Flyway flyway)
    {
        flyway.migrate();
        return configuration.buildSessionFactory();
    }

    @Bean
    public DozerBeanMapper getMapper()
    {
        return new DozerBeanMapper();
    }

    @Bean
    public Flyway getFlyWay()
    {
        ClassicConfiguration conf = new ClassicConfiguration();
        conf.setDataSource(dataSource);
        return new Flyway(conf);
    }

}
