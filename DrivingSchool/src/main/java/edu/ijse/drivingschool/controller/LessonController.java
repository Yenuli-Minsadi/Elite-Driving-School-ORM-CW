package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class LessonController {

    LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.LESSON);
}
