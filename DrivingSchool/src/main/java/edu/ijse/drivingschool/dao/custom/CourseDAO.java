package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Consultation;
import edu.ijse.drivingschool.entity.Coordinator;
import edu.ijse.drivingschool.entity.Course;

import java.util.List;

public interface CourseDAO extends CrudDAO<Course> {
    public boolean save(Course entity);
    public boolean update(Course entity);
    public boolean delete(String id);
    public List<Course> getAll();
}
