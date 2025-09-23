package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.dao.custom.LessonDAO;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.entity.*;

import java.util.List;

public class LessonBOImpl implements LessonBO {

    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LESSON);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public String getNextId() throws Exception {
        return lessonDAO.getNextId();
    }

    @Override
    public boolean save(LessonDTO lessonDTO) throws Exception{

        Registration registration = registrationDAO.getById(lessonDTO.getRegistrationId());
        Instructor instructor = instructorDAO.getById(lessonDTO.getInstructorId());

        return lessonDAO.save(new Lesson(
                lessonDTO.getLessonId(),
                lessonDTO.getLessonName(),
                lessonDTO.getLessonDescription(),
                registration,
                instructor,
                lessonDTO.getLessonDate(),
                lessonDTO.getLessonTime(),
                lessonDTO.getStatus())
        );
    }

    @Override
    public boolean update(LessonDTO lessonDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<LessonDTO> getAll() {
        return List.of();
    }
}
