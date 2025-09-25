package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.entity.Instructor;

import java.util.List;

public interface InstructorBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(InstructorDTO instructorDTO) throws Exception;
    public boolean update(InstructorDTO instructorDTO);
    public boolean delete(String id);
    public List<InstructorDTO> getAll();
    Instructor getById(String instructorId) throws Exception;

}
