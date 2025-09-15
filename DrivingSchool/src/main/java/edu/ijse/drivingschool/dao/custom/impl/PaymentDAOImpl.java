package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.PaymentDAO;
import edu.ijse.drivingschool.entity.Lesson;
import edu.ijse.drivingschool.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Payment entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Payment entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Payment payment = session.get(Payment.class,id);
            if (payment == null) {
                throw new Exception("Cannot delete payment, Payment ID not found!");
            }
            session.remove(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Payment> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Payment, Payment.class").list();
        } finally {
            session.close();
        }
    }
}
