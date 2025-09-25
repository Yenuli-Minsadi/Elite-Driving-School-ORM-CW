package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.UserDAO;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.entity.User;
import edu.ijse.drivingschool.exception.AccountLocked;
import edu.ijse.drivingschool.exception.DuplicateEntryException;
import edu.ijse.drivingschool.exception.InvalidCredentials;
import edu.ijse.drivingschool.util.PasswordUtil;
import edu.ijse.drivingschool.util.StudentFieldsValidator;
import edu.ijse.drivingschool.util.UserFieldsValidator;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getNextId() throws Exception {
        return userDAO.getNextId();
    }

    @Override
    public boolean save(UserDTO userDTO) throws Exception {

        UserFieldsValidator.fieldsValidate(userDTO);

        if (userDAO.getById(userDTO.getUserId()) != null) {
            throw new DuplicateEntryException("User with ID " + userDTO.getUserId() + " already exists.");
        }

        return userDAO.save(new User(userDTO.getUserId(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getUsername(), userDTO.getEmail(),
                userDTO.getPhone(), userDTO.getPassword(), userDTO.getRole()));
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getUsername(), userDTO.getEmail(),
                userDTO.getPhone(), userDTO.getPassword(), userDTO.getRole()));
    }

    @Override
    public boolean delete(String id) {
        return userDAO.delete(id);
    }

    @Override
    public User verifyUsername(String username) {
        return userDAO.verifyUsername(username);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> entity=userDAO.getAll();
        List<UserDTO>userDTO=new ArrayList<>();
        for( User user :entity){
            userDTO.add(new UserDTO(
                    user.getUserId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                    user.getEmail(), user.getPhone(), user.getPassword(), user.getRole()));
        }
        return userDTO;
    }

    @Override
    public User getById(String userId) throws Exception {
        return userDAO.getById(userId);
    }

    private int loggingAttempts;

    @Override
    public User verifyUser(String username, String password) {
        User logUser = userDAO.verifyUsername(username);

        if (logUser == null) {
            throw new InvalidCredentials("User cannot be found");
        }

        if (!PasswordUtil.matches(password,logUser.getPassword())) {
            loggingAttempts++;
            if (loggingAttempts >= 3) {
                throw new AccountLocked("3 failed attempts. Your account has been locked, please contact the admin.");
            }
            throw new InvalidCredentials("Invalid password");
        }
        loggingAttempts=0;
        return logUser;
    }
}
