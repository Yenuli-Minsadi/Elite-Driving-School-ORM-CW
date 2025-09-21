package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.CoordinatorBO;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class CourseController {

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.COURSE);
}
