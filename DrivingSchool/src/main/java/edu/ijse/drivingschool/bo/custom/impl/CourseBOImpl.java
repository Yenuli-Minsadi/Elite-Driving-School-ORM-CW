package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CourseDAO;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Instructor;
import edu.ijse.drivingschool.entity.Student;
import edu.ijse.drivingschool.exception.DuplicateEntryException;
import edu.ijse.drivingschool.util.CourseFieldsValidator;
import edu.ijse.drivingschool.util.StudentFieldsValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public String getNextId() throws Exception {
        return courseDAO.getNextId();
    }

    @Override
    public boolean save(CourseDTO courseDTO) throws Exception {

        CourseFieldsValidator.fieldsValidate(courseDTO);

        if (courseDAO.getById(courseDTO.getCourseId()) != null) {
            throw new DuplicateEntryException("Course with ID " + courseDTO.getCourseId() + " already exists.");
        }
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
        return courseDAO.update(new Course(
                courseDTO.getCourseId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseDescription(),
                courseDTO.getCourseType(),
                courseDTO.getCourseFee(),
                courseDTO.getDuration())
        );
    }

    @Override
    public boolean delete(String id) {
        return courseDAO.delete(id);
    }

    @Override
    public List<CourseDTO> getAll() {
        List<Course> entity=courseDAO.getAll();
        List<CourseDTO>courseDTO=new ArrayList<>();
        for(Course course :entity){
            courseDTO.add(new CourseDTO(
                    course.getCourseId(), course.getCourseName(), course.getDescription(), course.getCourseType(),
                    course.getCourseFee(), course.getDuration()));
        }
        return courseDTO;
    }

    @Override
    public Course getById(String courseId) throws Exception {
        return courseDAO.getById(courseId);
    }
}
