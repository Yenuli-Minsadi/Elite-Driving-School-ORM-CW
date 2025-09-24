package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CourseDAO;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.dao.custom.LessonDAO;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.*;

import java.util.ArrayList;
import java.util.List;

public class LessonBOImpl implements LessonBO {

    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LESSON);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public String getNextId() throws Exception {
        return lessonDAO.getNextId();
    }

    @Override
    public boolean save(LessonDTO lessonDTO) throws Exception{

        Registration registration = registrationDAO.getById(lessonDTO.getRegistrationId());
        Instructor instructor = instructorDAO.getById(lessonDTO.getInstructorId());
        Course course = courseDAO.getById(lessonDTO.getCourseId());

        return lessonDAO.save(new Lesson(
                lessonDTO.getLessonId(),
                lessonDTO.getLessonName(),
                lessonDTO.getLessonDescription(),
                registration,
                instructor,
                course,
                lessonDTO.getLessonDate(),
                lessonDTO.getLessonTime(),
                lessonDTO.getStatus())
        );
    }

    @Override
    public boolean update(LessonDTO lessonDTO) throws Exception {
        Registration registration = registrationDAO.getById(lessonDTO.getRegistrationId());
        Instructor instructor = instructorDAO.getById(lessonDTO.getInstructorId());
        Course course = courseDAO.getById(lessonDTO.getCourseId());

        return lessonDAO.update(new Lesson(
                lessonDTO.getLessonId(),
                lessonDTO.getLessonName(),
                lessonDTO.getLessonDescription(),
                registration,
                instructor,
                course,
                lessonDTO.getLessonDate(),
                lessonDTO.getLessonTime(),
                lessonDTO.getStatus())
        );
    }

    @Override
    public boolean delete(String id) {
        return lessonDAO.delete(id);
    }

    @Override
    public List<LessonDTO> getAll() {
        List<Lesson> entity=lessonDAO.getAll();
        List<LessonDTO>lessonDTO=new ArrayList<>();
        for( Lesson lesson :entity){
            lessonDTO.add(new LessonDTO(
                    lesson.getLessonId(), lesson.getLessonName(), lesson.getLessonDescription(), lesson.getRegistration(),
                    lesson.getInstructor(), lesson.getCourse(), lesson.getLessonDate(), lesson.getLessonTime(), lesson.getStatus()));
        }
        return lessonDTO;
    }
}
