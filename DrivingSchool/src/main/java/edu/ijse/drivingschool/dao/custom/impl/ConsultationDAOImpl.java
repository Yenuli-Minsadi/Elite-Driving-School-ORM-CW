package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.entity.Consultation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.DuplicateFormatFlagsException;
import java.util.List;


public class ConsultationDAOImpl implements ConsultationDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();//Property injection

    @Override
    public boolean save(Consultation entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Consultation existConsultation = session.get(Consultation.class, entity.getConsultationId());

            if (existConsultation != null) {
                throw new Exception("Duplicate Consultation, it already exists.");
            }
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Consultation entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Consultation existConsultation = session.get(Consultation.class, entity.getConsultationId());

            if (existConsultation == null) {
                throw new Exception("Consultation doesn't exist, Consultation ID not found!");
            }
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
//            throw new RuntimeException(e);
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
            Consultation existConsultation = session.get(Consultation.class, id);
            if (existConsultation == null) {
                throw new Exception("Cannot delete consultation, Consultation ID not found!");
            }
            session.remove(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            transaction.rollback();
            return false;
        }


    }

    @Override
    public List<Consultation> getAll() {
        return List.of();
    }
}
