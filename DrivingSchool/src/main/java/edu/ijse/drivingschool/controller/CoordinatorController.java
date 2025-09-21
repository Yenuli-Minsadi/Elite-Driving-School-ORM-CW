package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.CoordinatorBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class CoordinatorController {

    CoordinatorBO coordinatorBO = (CoordinatorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.COORDINATOR);
}
