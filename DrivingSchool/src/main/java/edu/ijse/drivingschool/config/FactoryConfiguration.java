package edu.ijse.drivingschool.config;

import edu.ijse.drivingschool.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

import org.hibernate.cfg.Configuration;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load hibernate.properties",e);
        }

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Registration.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Lesson.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory=configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
