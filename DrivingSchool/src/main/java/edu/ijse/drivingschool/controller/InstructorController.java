package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class InstructorController {

    InstructorBO instructorBO = (InstructorBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
}
