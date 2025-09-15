package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.LessonDAO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LessonDAOImpl implements LessonDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Lesson entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Lesson existLesson = session.get(Lesson.class, entity.getLessonId());

            if (existLesson != null) {
                throw new Exception("Lesson already exists.");
            }
//            if (entity.getLessonId() == null || entity.getLessonName() == null ||
//                    entity.getLessonDescription() == null || entity.getCourseId() == null ||
//                    entity.getInstructorId() == null || entity.getStatus() == null) {
//                throw new Exception("All fields must be completed before saving.");
//            } //take this to bo layer
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
    public boolean update(Lesson entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Lesson existLesson = session.get(Lesson.class, entity.getLessonId());

            if (existLesson == null) {
                throw new Exception("Lesson doesn't exist, Lesson ID not found!");
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
            Lesson lesson = session.get(Lesson.class,id);
            if (lesson == null) {
                throw new Exception("Cannot delete lesson, Lesson ID not found!");
            }
            session.remove(lesson);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Lesson> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM Lesson, Lesson.class").list();
        } finally {
            session.close();
        }
    }
}
