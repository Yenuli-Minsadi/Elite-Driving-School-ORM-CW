package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.entity.Coordinator;

import java.util.List;

public class CoordinatorDAOImpl implements CoordinatorDAO {
    @Override
    public boolean save(Coordinator entity) {
        return false;
    }

    @Override
    public boolean update(Coordinator entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Coordinator> getAll() {
        return List.of();
    }
}
