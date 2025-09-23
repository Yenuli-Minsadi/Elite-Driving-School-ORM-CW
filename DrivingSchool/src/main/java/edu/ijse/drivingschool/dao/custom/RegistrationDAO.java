package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Registration;

import java.util.List;

public interface RegistrationDAO extends CrudDAO<Registration> {
    public String getNextId() throws Exception;
    public boolean save(Registration entity);
    public boolean update(Registration entity);
    public boolean delete(String id);
    public List<Registration> getAll();
    Registration getById(String registrationId) throws Exception;
}
