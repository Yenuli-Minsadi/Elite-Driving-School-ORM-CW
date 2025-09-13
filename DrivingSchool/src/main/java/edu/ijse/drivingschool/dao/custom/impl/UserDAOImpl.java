package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.UserDAO;
import edu.ijse.drivingschool.entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }
}
