package edu.ijse.drivingschool.dao;

import edu.ijse.drivingschool.dao.custom.impl.*;
import org.hibernate.query.Query;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CONSULTATION,
        COORDINATOR,
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT,
        REGISTRATION,
        STUDENT,
        USER,
        QUERY
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case COURSE:
                return new CourseDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case LESSON:
                return new LessonDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
