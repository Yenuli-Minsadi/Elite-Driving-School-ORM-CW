package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Student;
import edu.ijse.drivingschool.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    public String getNextId() throws Exception;
    public boolean save(User entity);
    public boolean update(User entity);
    public boolean delete(String id);
    public User verifyUsername(String username);
    public List<User> getAll();
    User getById(String userId) throws Exception;
}
