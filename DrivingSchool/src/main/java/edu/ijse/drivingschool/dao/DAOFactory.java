package edu.ijse.drivingschool.dao;

import edu.ijse.drivingschool.dao.custom.impl.*;

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
        INSTRUCTOR,
        LESSON,
        PAYMENT,
        REGISTRATION,
        STUDENT,
        USER
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case CONSULTATION:
                return new ConsultationDAOImpl();
            case COORDINATOR:
                return new CoordinatorDAOImpl();
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
            default:
                return null;
        }
    }
}
