package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    public String getNextId() throws Exception;
    public boolean save(Student entity);
    public boolean update(Student entity);
    public boolean delete(String id);
    public List<Student> getAll();
}
