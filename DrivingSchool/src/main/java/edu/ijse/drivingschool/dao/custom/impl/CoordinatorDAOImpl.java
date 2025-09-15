package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.entity.Consultation;
import edu.ijse.drivingschool.entity.Coordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CoordinatorDAOImpl implements CoordinatorDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Coordinator entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Coordinator existCoordinator = session.get(Coordinator.class, entity.getCoordinatorId());

            if (existCoordinator != null) {
                throw new Exception("Coordinator already exists.");
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
    public boolean update(Coordinator entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Coordinator existCoordinator = session.get(Coordinator.class, entity.getCoordinatorId());

            if (existCoordinator == null) {
                throw new Exception("Coordinator doesn't exist, Coordinator ID not found!");
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
            Coordinator coordinator = session.get(Coordinator.class, id);
            if (coordinator == null) {
                throw new Exception("Cannot delete coordinator, Coordinator ID not found!");
            }
            session.remove(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Coordinator> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Coordinator, Coordinator.class").list();
        } finally {
            session.close();
        }
    }
}
