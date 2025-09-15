package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.dao.DAOFactory;

public class PaymentController {

    PaymentBO paymentBO = (PaymentBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
}
