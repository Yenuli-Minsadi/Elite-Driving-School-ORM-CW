package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.entity.Student;
import edu.ijse.drivingschool.entity.User;

import java.util.List;

public interface UserBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(UserDTO userDTO) throws Exception;
    public boolean update(UserDTO userDTO);
    public boolean delete(String id);
    public User verifyUsername(String username);
    public List<UserDTO> getAll();
    User getById(String userId) throws Exception;

}
