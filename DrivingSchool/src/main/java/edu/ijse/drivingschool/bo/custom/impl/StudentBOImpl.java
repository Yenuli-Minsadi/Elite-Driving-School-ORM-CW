package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.StudentDAO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;

import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String getNextId() throws Exception {
        return "";
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        return false;
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<StudentDTO> getAll() {
        return List.of();
    }
}
