package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Consultation;
import edu.ijse.drivingschool.entity.Coordinator;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    public boolean save(Student entity);
    public boolean update(Student entity);
    public boolean delete(String id);
    public List<Student> getAll();
}
