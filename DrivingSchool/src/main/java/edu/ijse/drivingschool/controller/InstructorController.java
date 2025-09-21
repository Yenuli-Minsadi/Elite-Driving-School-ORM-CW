package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class InstructorController {

    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.INSTRUCTOR);
}
