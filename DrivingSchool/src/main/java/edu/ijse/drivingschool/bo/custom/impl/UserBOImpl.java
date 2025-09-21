package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.UserDAO;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.entity.User;

import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getNextId() throws Exception {
        return userDAO.getNextId();
    }

    @Override
    public boolean save(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getUsername(), userDTO.getEmail(),
                userDTO.getPhone(), userDTO.getPassword(), userDTO.getRole()));
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
    public User verifyUsername(String username) {
        return userDAO.verifyUsername(username);
    }

    @Override
    public List<UserDTO> getAll() {
        return List.of();
    }
}
