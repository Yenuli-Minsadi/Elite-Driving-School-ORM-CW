package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.entity.User;

public class UserController {

    UserBO userBO = (UserBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
}
