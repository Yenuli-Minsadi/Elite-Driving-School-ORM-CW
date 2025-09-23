package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.entity.Instructor;
import edu.ijse.drivingschool.entity.Registration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws Exception {
        Session session = factoryConfiguration.getSession();

        String nextId = null;
        try {
            String lastId = session.createQuery
                            ("SELECT i.instructorId FROM Instructor i ORDER BY  i.instructorId DESC", String.class)
                    .setMaxResults(1).uniqueResult();

            nextId = (lastId == null) ? "I001" : String.format("I%03d", Integer.parseInt(lastId.substring(1))+1);

        } finally {
            session.close();
        }
        return nextId;
    }

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
            Instructor instructor = session.get(Instructor.class, id);
            if (instructor == null) {
                throw new Exception("Cannot delete instructor, Instructor ID not found!");
            }
            session.remove(instructor);
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
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Instructor", Instructor.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Instructor getById(String instructorId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.get(Instructor.class, instructorId);
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to fetch student with id" + instructorId, e);
        }
    }
}
