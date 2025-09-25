package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public interface QueryBO extends SuperBO {
    int getStudentsRegisteredForAllCourses() throws Exception;
}
