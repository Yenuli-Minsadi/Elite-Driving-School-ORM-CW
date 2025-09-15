package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Consultation;
import edu.ijse.drivingschool.entity.Coordinator;
import edu.ijse.drivingschool.entity.Instructor;

import java.util.List;

public interface InstructorDAO extends CrudDAO<Instructor> {
    public boolean save(Instructor entity);
    public boolean update(Instructor entity);
    public boolean delete(String id);
    public List<Instructor> getAll();
}
