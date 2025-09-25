package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.entity.Instructor;
import edu.ijse.drivingschool.entity.Lesson;
import edu.ijse.drivingschool.exception.DuplicateEntryException;

import java.util.ArrayList;
import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public String getNextId() throws Exception {
        return instructorDAO.getNextId();
    }

    @Override
    public boolean save(InstructorDTO instructorDTO) throws Exception {

        if (instructorDAO.getById(instructorDTO.getInstructorId()) != null) {
            throw new DuplicateEntryException("Instructor with ID " + instructorDTO.getInstructorId() + " already exists.");
        }

        return instructorDAO.save(new Instructor(
                instructorDTO.getInstructorId(),
                instructorDTO.getFirstName(),
                instructorDTO.getLastName(),
                instructorDTO.getSpecialization(),
                instructorDTO.getPhone(),
                instructorDTO.getEmail(),
                instructorDTO.getAddress(),
                instructorDTO.getAvailability()
        ));
    }

    @Override
    public boolean update(InstructorDTO instructorDTO) {
        return instructorDAO.update(new Instructor(
                instructorDTO.getInstructorId(),
                instructorDTO.getFirstName(),
                instructorDTO.getLastName(),
                instructorDTO.getSpecialization(),
                instructorDTO.getPhone(),
                instructorDTO.getEmail(),
                instructorDTO.getAddress(),
                instructorDTO.getAvailability()
        ));
    }

    @Override
    public boolean delete(String id) {
        return instructorDAO.delete(id);
    }

    @Override
    public List<InstructorDTO> getAll() {
        List<Instructor> entity=instructorDAO.getAll();
        List<InstructorDTO>instructorDTO=new ArrayList<>();
        for( Instructor instructor :entity){
            instructorDTO.add(new InstructorDTO(
                    instructor.getInstructorId(), instructor.getFirstName(), instructor.getLastName(), instructor.getSpecialization(),
                    instructor.getPhone(), instructor.getEmail(), instructor.getAddress(), instructor.getAvailability()));
        }
        return instructorDTO;
    }

    @Override
    public Instructor getById(String instructorId) throws Exception {
        return instructorDAO.getById(instructorId);
    }
}
