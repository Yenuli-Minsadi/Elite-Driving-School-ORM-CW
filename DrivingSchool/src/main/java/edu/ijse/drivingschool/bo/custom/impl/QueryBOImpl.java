package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.QueryBO;
import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.QueryDAO;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class QueryBOImpl implements QueryBO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public int getStudentsRegisteredForAllCourses() throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        int count;

        try {
            count = queryDAO.getStudentsRegisteredForAllCourses(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

    @Override
    public int getOngoingLessonsCount() throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        int count;

        try {
            count = queryDAO.getOngoingLessonsCount(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

//    @Override
//    public int getStudentsRegisteredForAllCourses() throws Exception {
////        return queryDAO.getStudentsRegisteredForAllCourses();
//        Session session = factoryConfiguration.getSession();
//        Transaction transaction = session.beginTransaction();
//        int count;
//
//        try {
//            count = queryDAO.getStudentsRegisteredForAllCourses(session);
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
//        return count;
//    }

//    @Override
//    public int getStudentsRegisteredForAllCourses() throws Exception {
////        return queryDAO.getStudentsRegisteredForAllCourses();
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
