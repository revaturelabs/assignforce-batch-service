package com.revature.assignforce.config;

import com.revature.assignforce.beans.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({})
public class H2Config {

    @Autowired
    private ApplicationContext context;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        System.out.println("Config SessionFactory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[] { "com.revature.assignforce.beans", "com.revature.assignforce.repos.revaturepro" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties settings = new Properties();
        settings.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        settings.setProperty("hibernate.show_sql", "true");
        settings.setProperty("hibernate.format_sql", "true");
        settings.setProperty("hibernate.hbm2ddl.auto", "update");
        return settings;
    }

    @Bean
    @Qualifier(value = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager){
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManager);
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

}
