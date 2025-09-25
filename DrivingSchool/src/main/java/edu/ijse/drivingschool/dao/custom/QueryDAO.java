package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public interface QueryDAO extends StudentDAO{
    int getStudentsRegisteredForAllCourses() throws Exception;
}
