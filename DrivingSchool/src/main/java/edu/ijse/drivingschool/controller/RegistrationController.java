package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.bo.custom.RegistrationBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class RegistrationController {

    RegistrationBO registrationBO = (RegistrationBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
}
