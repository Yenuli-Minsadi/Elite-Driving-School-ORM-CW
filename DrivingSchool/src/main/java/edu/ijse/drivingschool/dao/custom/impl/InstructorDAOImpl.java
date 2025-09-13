package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.entity.Coordinator;
import edu.ijse.drivingschool.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Instructor entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Instructor existInstructor = session.get(Instructor.class, entity.getInstructorId());

            if (existInstructor != null) {
                throw new Exception("Instructor already exists.");
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
    public boolean update(Instructor entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Instructor existInstructor = session.get(Instructor.class, entity.getInstructorId());

            if (existInstructor == null) {
                throw new Exception("Instructor doesn't exist, Instructor ID not found!");
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
            Instructor existInstructor = session.get(Instructor.class, id);
            if (existInstructor == null) {
                throw new Exception("Cannot delete instructor, Instructor ID not found!");
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
    public List<Instructor> getAll() {
        return List.of();
    }
}
