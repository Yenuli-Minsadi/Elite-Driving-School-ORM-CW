package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.RegistrationBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.dto.RegistrationDTO;

import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);

    @Override
    public boolean save(RegistrationDTO registrationDTO) {
        return false;
    }

    @Override
    public boolean update(RegistrationDTO registrationDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<RegistrationDTO> getAll() {
        return List.of();
    }
}
