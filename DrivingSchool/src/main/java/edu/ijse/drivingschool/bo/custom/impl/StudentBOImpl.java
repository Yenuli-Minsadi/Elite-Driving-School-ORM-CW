package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.StudentDAO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.entity.Student;
import edu.ijse.drivingschool.entity.User;
import edu.ijse.drivingschool.exception.DuplicateEntryException;
import edu.ijse.drivingschool.exception.MissingFields;
import edu.ijse.drivingschool.util.StudentFieldsValidator;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String getNextId() throws Exception {
        return studentDAO.getNextId();
    }

    @Override
    public boolean save(StudentDTO studentDTO) throws Exception {
        StudentFieldsValidator.fieldsValidate(studentDTO);
        if (studentDAO.getById(studentDTO.getStudentId()) != null) {
            throw new DuplicateEntryException("Student with ID " + studentDTO.getStudentId() + " already exists.");
        }
        return studentDAO.save(new Student(studentDTO.getStudentId(),
                studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getEmail(),
                studentDTO.getPhone(), studentDTO.getAddress(), studentDTO.getDob()));
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudentId(),
                studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getEmail(),
                studentDTO.getPhone(), studentDTO.getAddress(), studentDTO.getDob()));
    }

    @Override
    public boolean delete(String id) {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDTO> getAll() {
        List<Student> entity=studentDAO.getAll();
        List<StudentDTO>studentDTO=new ArrayList<>();
        for( Student student :entity){
            studentDTO.add(new StudentDTO(
                    student.getStudentId(), student.getFirstName(), student.getLastName(), student.getEmail(),
                    student.getPhone(), student.getAddress(), student.getDob()));
        }
        return studentDTO;
    }

    @Override
    public Student getById(String studentId) throws Exception {
        return studentDAO.getById(studentId);
    }
}
