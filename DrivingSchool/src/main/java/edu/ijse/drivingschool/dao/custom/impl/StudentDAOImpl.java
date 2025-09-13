package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.StudentDAO;
import edu.ijse.drivingschool.entity.Coordinator;
import edu.ijse.drivingschool.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Student entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Student existStudent = session.get(Student.class, entity.getStudentId());

            if (existStudent != null) {
                throw new Exception("Student already exists.");
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
    public boolean update(Student entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Student existStudent = session.get(Student.class, entity.getStudentId());

            if (existStudent == null) {
                throw new Exception("Student doesn't exist, Student ID not found!");
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
            Student existStudent = session.get(Student.class,id);
            if (existStudent == null) {
                throw new Exception("Cannot delete student, Student ID not found!");
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
    public List<Student> getAll() {
        return List.of();
    }
}
