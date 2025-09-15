package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.CoordinatorBO;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class CourseController {

    CourseBO courseBO = (CourseBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);
}
