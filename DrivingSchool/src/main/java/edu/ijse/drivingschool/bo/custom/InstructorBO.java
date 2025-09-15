package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.ConsultationDTO;
import edu.ijse.drivingschool.dto.InstructorDTO;

import java.util.List;

public interface InstructorBO extends SuperBO {

    public boolean save(InstructorDTO instructorDTO);
    public boolean update(InstructorDTO instructorDTO);
    public boolean delete(String id);
    public List<InstructorDTO> getAll();

}
