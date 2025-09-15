package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dto.ConsultationDTO;

import java.util.List;

public class ConsultationBOImpl implements ConsultationBO {

    ConsultationDAO consultationDAO = (ConsultationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CONSULTATION);


    @Override
    public boolean save(ConsultationDTO consultationDTO) {
        return false;
    }

    @Override
    public boolean update(ConsultationDTO consultationDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<ConsultationDTO> getAll() {
        return List.of();
    }
}
