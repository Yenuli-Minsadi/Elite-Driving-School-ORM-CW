package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.entity.Registration;
import edu.ijse.drivingschool.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws Exception {
        Session session = factoryConfiguration.getSession();

        String nextId = null;
        try {
            String lastId = session.createQuery
                            ("SELECT r.registrationId FROM Registration r ORDER BY r.registrationId DESC", String.class)
                    .setMaxResults(1).uniqueResult();

            nextId = (lastId == null) ? "R001" : String.format("R%03d", Integer.parseInt(lastId.substring(1))+1);

        } finally {
            session.close();
        }
        return nextId;
    }

    @Override
    public boolean save(Registration entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Registration existRegistration = session.get(Registration.class, entity.getRegistrationId());

            if (existRegistration != null) {
                throw new Exception("Registration already exists.");
            }
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Registration entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Registration existRegistration = session.get(Registration.class, entity.getRegistrationId());

            if (existRegistration == null) {
                throw new Exception("Registration doesn't exist, Registration ID not found!");
            }
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Registration registration = session.get(Registration.class,id);
            if (registration == null) {
                throw new Exception("Cannot delete registration, Registration ID not found!");
            }
            session.remove(registration);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Registration> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Registration", Registration.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Registration getById(String registrationId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.get(Registration.class, registrationId);
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to fetch student with id" + registrationId, e);
        }
    }
}
