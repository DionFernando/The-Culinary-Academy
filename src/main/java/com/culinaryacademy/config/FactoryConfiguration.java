package com.culinaryacademy.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        // Set Hibernate properties programmatically
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        hibernateProperties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/culinary_academy_management");
        hibernateProperties.setProperty("hibernate.connection.username", "root");
        hibernateProperties.setProperty("hibernate.connection.password", "Ijse@123");

        // Additional Hibernate settings
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");

        // Caching settings (optional)
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
        hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

        // Configure Hibernate with properties and add annotated classes
        Configuration configuration = new Configuration()
                .addProperties(hibernateProperties)
                .addAnnotatedClass(com.culinaryacademy.entity.Program.class)
                .addAnnotatedClass(com.culinaryacademy.entity.Student.class)
                .addAnnotatedClass(com.culinaryacademy.entity.StudentProgram.class)
                .addAnnotatedClass(com.culinaryacademy.entity.User.class)
                .addAnnotatedClass(com.culinaryacademy.entity.Payment.class);

        // Build the session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
