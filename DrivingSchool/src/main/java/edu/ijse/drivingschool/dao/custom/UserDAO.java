package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Consultation;
import edu.ijse.drivingschool.entity.Coordinator;
import edu.ijse.drivingschool.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    public boolean save(User entity);
    public boolean update(User entity);
    public boolean delete(String id);
    public User verifyUsername(String username);
    public List<User> getAll();
}
