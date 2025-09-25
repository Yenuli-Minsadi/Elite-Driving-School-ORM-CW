package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.LessonDAO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Instructor;
import edu.ijse.drivingschool.entity.Lesson;
import edu.ijse.drivingschool.entity.Registration;
import edu.ijse.drivingschool.exception.SchedulingConflicts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LessonDAOImpl implements LessonDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws Exception {
        Session session = factoryConfiguration.getSession();

        String nextId = null;
        try {
            String lastId = session.createQuery
                            ("SELECT l.lessonId FROM Lesson l ORDER BY l.lessonId DESC", String.class)
                    .setMaxResults(1).uniqueResult();

            nextId = (lastId == null) ? "L001" : String.format("L%03d", Integer.parseInt(lastId.substring(1))+1);

        } finally {
            session.close();
        }
        return nextId;
    }

    // LessonDAOImpl.java
    @Override
    public boolean save(Lesson lesson) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()) {

            // Make sure the associations are managed entities
            Instructor managedInstructor = session.get(Instructor.class, lesson.getInstructor().getInstructorId());
            Registration managedRegistration = session.get(Registration.class, lesson.getRegistration().getRegistrationId());
            Course managedCourse = session.get(Course.class, lesson.getCourse().getCourseId());

            if (managedInstructor == null || managedRegistration == null || managedCourse == null) {
                throw new SchedulingConflicts("Instructor, Registration, or Course not found in DB!");
            }

            // Set managed entities
            lesson.setInstructor(managedInstructor);
            lesson.setRegistration(managedRegistration);
            lesson.setCourse(managedCourse);

            transaction = session.beginTransaction();
            session.persist(lesson);
            transaction.commit();
            return true;

        } catch (SchedulingConflicts e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
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
            return session.createQuery("FROM Lesson", Lesson.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Lesson getById(String lessonId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.get(Lesson.class, lessonId);
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to fetch student with id" + lessonId, e);
        }
    }
}
