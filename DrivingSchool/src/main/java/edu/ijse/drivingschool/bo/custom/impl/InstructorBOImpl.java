package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.dto.InstructorDTO;

import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public boolean save(InstructorDTO instructorDTO) {
        return false;
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
}
