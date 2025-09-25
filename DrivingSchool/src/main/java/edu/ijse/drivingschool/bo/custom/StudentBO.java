package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public interface StudentBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(StudentDTO studentDTO) throws Exception;
    public boolean update(StudentDTO studentDTO);
    public boolean delete(String id);
    public List<StudentDTO> getAll();
    Student getById(String studentId) throws Exception;

}
