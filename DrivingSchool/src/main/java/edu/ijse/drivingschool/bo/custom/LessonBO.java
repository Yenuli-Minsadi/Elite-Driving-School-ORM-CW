package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.ConsultationDTO;
import edu.ijse.drivingschool.dto.LessonDTO;

import java.util.List;

public interface LessonBO extends SuperBO {

    public boolean save(LessonDTO lessonDTO);
    public boolean update(LessonDTO lessonDTO);
    public boolean delete(String id);
    public List<LessonDTO> getAll();

}
