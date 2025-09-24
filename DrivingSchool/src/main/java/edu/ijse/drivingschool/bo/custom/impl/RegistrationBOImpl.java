package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.RegistrationBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CourseDAO;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.dao.custom.StudentDAO;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Registration;
import edu.ijse.drivingschool.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);


    @Override
    public String getNextId() throws Exception {
        return registrationDAO.getNextId();
    }

    @Override
    public boolean save(RegistrationDTO registrationDTO) throws Exception{

        Student student = studentDAO.getById(registrationDTO.getStudentId());
        Course course = courseDAO.getById(registrationDTO.getCourseId());

        return registrationDAO.save(new Registration(
                registrationDTO.getRegistrationId(),
                student,
                course,
                registrationDTO.getRegistrationDate())
        );
    }

    @Override
    public boolean update(RegistrationDTO registrationDTO) throws Exception {
        Student student = studentDAO.getById(registrationDTO.getStudentId());
        Course course = courseDAO.getById(registrationDTO.getCourseId());

        return registrationDAO.save(new Registration(
                registrationDTO.getRegistrationId(),
                student,
                course,
                registrationDTO.getRegistrationDate())
        );
    }

    @Override
    public boolean delete(String id) {
        return registrationDAO.delete(id);
    }

    @Override
    public List<RegistrationDTO> getAll() {
        List<Registration> entity=registrationDAO.getAll();
        List<RegistrationDTO>registrationDTO=new ArrayList<>();
        for( Registration registration :entity){
            registrationDTO.add(new RegistrationDTO(
                    registration.getRegistrationId(), registration.getStudent(), registration.getCourse(), registration.getRegistrationDate()));
        }
        return registrationDTO;
    }

    @Override
    public Registration getById(String registrationId) throws Exception {
        return registrationDAO.getById(registrationId);
    }
}
