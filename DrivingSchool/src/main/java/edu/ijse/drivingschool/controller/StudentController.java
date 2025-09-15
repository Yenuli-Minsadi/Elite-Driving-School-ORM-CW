package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.RegistrationBO;
import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class StudentController {

    StudentBO studentBO = (StudentBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
}
