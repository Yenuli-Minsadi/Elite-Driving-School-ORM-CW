package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CourseDAO;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public String getNextId() throws Exception {
        return courseDAO.getNextId();
    }

    @Override
    public boolean save(CourseDTO courseDTO) {
        return courseDAO.save(new Course(
                courseDTO.getCourseId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseDescription(),
                courseDTO.getCourseType(),
                courseDTO.getCourseFee(),
                courseDTO.getDuration())
        );
    }

    @Override
    public boolean update(CourseDTO courseDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<CourseDTO> getAll() {
        return List.of();
    }

    @Override
    public Course getById(String courseId) throws Exception {
        return courseDAO.getById(courseId);
    }
}
