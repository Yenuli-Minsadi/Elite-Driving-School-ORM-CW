package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.LessonDAO;
import edu.ijse.drivingschool.dto.LessonDTO;

import java.util.List;

public class LessonBOImpl implements LessonBO {

    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LESSON);

    @Override
    public String getNextId() throws Exception {
        return "";
    }

    @Override
    public boolean save(LessonDTO lessonDTO) {
        return false;
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
