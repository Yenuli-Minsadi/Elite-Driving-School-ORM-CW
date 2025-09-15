package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.CoordinatorBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.dto.ConsultationDTO;
import edu.ijse.drivingschool.dto.CoordinatorDTO;
import edu.ijse.drivingschool.entity.Coordinator;

import java.util.List;

public class CoordinatorBOImpl implements CoordinatorBO {

    CoordinatorDAO coordinatorDAO = (CoordinatorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COORDINATOR);

    @Override
    public boolean save(CoordinatorDTO coordinatorDTO) {
        return false;
    }

    @Override
    public boolean update(CoordinatorDTO coordinatorDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<CoordinatorDTO> getAll() {
        return List.of();
    }
}
