package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.QueryDAO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public String getNextId() throws Exception {
        return "";
    }

    @Override
    public boolean save(Student entity) {
        return false;
    }

    @Override
    public boolean update(Student entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Student> getAll() {
        return List.of();
    }

    @Override
    public Student getById(String studentId) throws Exception {
        return null;
    }

    @Override
    public int getStudentsRegisteredForAllCourses(Session session) throws Exception {
        // HQL: join students with registrations, group by student, check if they have all courses
        String hql = """
        SELECT s.id
        FROM Student s
        JOIN s.registration r
        GROUP BY s.id
        HAVING COUNT(DISTINCT r.course.id) = (SELECT COUNT(c.id) FROM Course c)
    """;

        List<String> studentIds = session.createQuery(hql, String.class).getResultList();
        return studentIds.size();
    }

    @Override
    public int getOngoingLessonsCount(Session session) throws Exception {
        String hql = "SELECT COUNT(l) FROM Lesson l WHERE l.status = :status";

        Long count = session.createQuery(hql, Long.class)
                .setParameter("status", "On Going")
                .getSingleResult();

        return count.intValue();
    }



//    @Override
//    public int getStudentsRegisteredForAllCourses(Session session) throws Exception {
//            String hql = """
//            SELECT COUNT(s)
//            FROM Student s
//            WHERE (SELECT COUNT(r.course)
//                   FROM Registration r
//                   WHERE r.student = s) = (SELECT COUNT(c) FROM Course c)
//        """;
//            Long count = session.createQuery(hql, Long.class).getSingleResult();
//            return count.intValue();
//    }

//    @Override
//    public int getStudentsRegisteredForAllCourses() throws Exception {
//        Session session = factoryConfiguration.getSession();
//        Transaction transaction = session.beginTransaction();
//        Long count;
//
//        try {
//            String hql = """
//            SELECT COUNT(s)
//            FROM Student s
//            WHERE (SELECT COUNT(r.course)
//                   FROM Registration r
//                   WHERE r.student = s) = (SELECT COUNT(c) FROM Course c)
//        """;
//            count = session.createQuery(hql, Long.class).getSingleResult();
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
//        return count.intValue();
//    }
}
