package edu.ijse.drivingschool.bo;

import edu.ijse.drivingschool.bo.custom.impl.*;
import edu.ijse.drivingschool.dao.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
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

    public SuperBO getBo(BOFactory.BOTypes boType) {
        switch(boType) {
            case COURSE:
                return new CourseBOImpl();
            case INSTRUCTOR:
                return new InstructorBOImpl();
            case LESSON:
                return new LessonBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case USER:
                return new UserBOImpl();
            case QUERY:
                return new QueryBOImpl();
            default:
                return null;
        }
    }
}
