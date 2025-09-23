package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.entity.Instructor;

import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public String getNextId() throws Exception {
        return instructorDAO.getNextId();
    }

    @Override
    public boolean save(InstructorDTO instructorDTO) {
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
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<InstructorDTO> getAll() {
        return List.of();
    }

    @Override
    public Instructor getById(String instructorId) throws Exception {
        return instructorDAO.getById(instructorId);
    }
}
