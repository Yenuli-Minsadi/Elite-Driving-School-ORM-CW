package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.entity.Lesson;
import edu.ijse.drivingschool.entity.Payment;

import java.util.List;

public interface LessonBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(LessonDTO lessonDTO) throws Exception;
    public boolean update(LessonDTO lessonDTO) throws Exception;
    public boolean delete(String id);
    public List<LessonDTO> getAll();
    Lesson getById(String lessonId) throws Exception;

}
