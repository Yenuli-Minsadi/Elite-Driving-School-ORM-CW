package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public interface CourseBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(CourseDTO courseDTO) throws Exception;
    public boolean update(CourseDTO courseDTO);
    public boolean delete(String id);
    public List<CourseDTO> getAll();
    Course getById(String courseId) throws Exception;

}
