package org.example.jsftest.configuration;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.example.jsftest.util.SnakeCasePhysicalNamingStrategy;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Data
public class AppConfigBeans
{
    @Resource(lookup = "java:/testDB")
    DataSource dataSource;

    @Bean
    public PhysicalNamingStrategy getStrategy()
    {
        return new SnakeCasePhysicalNamingStrategy();
    }

    @Bean
    public DozerBeanMapper getMapper()
    {
        return new DozerBeanMapper();
    }

    @Bean(name = "flyway")
    public Flyway getFlyWay()
    {
        ClassicConfiguration conf = new ClassicConfiguration();
        conf.setDataSource(dataSource);
        Flyway flyway = new Flyway(conf);
        flyway.migrate();
        return flyway;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(Flyway flyway, PhysicalNamingStrategy physicalNamingStrategy)
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.example.jsftest.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPhysicalNamingStrategy(physicalNamingStrategy);
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactoryBean)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean.getObject());
        return transactionManager;
    }


    public Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        hibernateProperties.setProperty("hibernate.connection.datasource", "java:/testDB");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
        return hibernateProperties;
    }


}
