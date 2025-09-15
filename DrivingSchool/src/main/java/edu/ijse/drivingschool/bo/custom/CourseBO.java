package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.ConsultationDTO;
import edu.ijse.drivingschool.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {

    public boolean save(CourseDTO courseDTO);
    public boolean update(CourseDTO courseDTO);
    public boolean delete(String id);
    public List<CourseDTO> getAll();

}
