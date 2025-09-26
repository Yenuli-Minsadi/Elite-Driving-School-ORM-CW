package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO extends StudentDAO{
    int getStudentsRegisteredForAllCourses(Session session) throws Exception;
    int getOngoingLessonsCount(Session session) throws Exception;
}
