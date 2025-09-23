package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.StudentDAO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;

import javax.print.DocFlavor;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String getNextId() throws Exception {
        return studentDAO.getNextId();
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudentId(),
                studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getEmail(),
                studentDTO.getPhone(), studentDTO.getAddress(), studentDTO.getDob()));
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

    @Override
    public Student getById(String studentId) throws Exception {
        return studentDAO.getById(studentId);
    }
}
