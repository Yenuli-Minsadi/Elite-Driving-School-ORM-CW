package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.StudentDAO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws Exception {
        Session session = factoryConfiguration.getSession();

        String nextId = null;
        try {
            String lastId = session.createQuery
                            ("SELECT s.studentId FROM Student s ORDER BY s.studentId DESC", String.class)
                    .setMaxResults(1).uniqueResult();

            nextId = (lastId == null) ? "S001" : String.format("S%03d", Integer.parseInt(lastId.substring(1))+1);

        } finally {
            session.close();
        }
        return nextId;
    }

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
            Student student = session.get(Student.class,id);
            if (student == null) {
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
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Student", Student.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Student getById(String studentId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.get(Student.class, studentId);
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to fetch student with id" + studentId, e);
        }
    }
}
