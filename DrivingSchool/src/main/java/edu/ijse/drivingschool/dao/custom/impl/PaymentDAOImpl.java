package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.PaymentDAO;
import edu.ijse.drivingschool.entity.Lesson;
import edu.ijse.drivingschool.entity.Payment;
import edu.ijse.drivingschool.entity.Registration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws Exception {
        Session session = factoryConfiguration.getSession();

        String nextId = null;
        try {
            String lastId = session.createQuery
                            ("SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC", String.class)
                    .setMaxResults(1).uniqueResult();

            nextId = (lastId == null) ? "P001" : String.format("P%03d", Integer.parseInt(lastId.substring(1))+1);

        } finally {
            session.close();
        }
        return nextId;
    }

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
            session.merge(entity);
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
        } finally {
            session.close();
        }
    }

    @Override
    public List<Payment> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Payment", Payment.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Payment getById(String paymentId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.get(Payment.class, paymentId);
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to fetch student with id" + paymentId, e);
        }
    }
}
