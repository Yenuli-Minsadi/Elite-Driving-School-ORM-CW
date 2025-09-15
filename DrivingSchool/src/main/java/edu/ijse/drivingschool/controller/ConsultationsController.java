package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.UserDAO;

public class ConsultationsController {

    ConsultationBO consultationBO = (ConsultationBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CONSULTATION);
}
