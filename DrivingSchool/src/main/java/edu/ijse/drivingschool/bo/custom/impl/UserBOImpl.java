package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.dao.custom.UserDAO;
import edu.ijse.drivingschool.dto.UserDTO;

import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean save(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<UserDTO> getAll() {
        return List.of();
    }
}
