package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.entity.Consultation;

import java.util.List;

public class ConsultationDAOImpl implements ConsultationDAO {
    @Override
    public boolean save(Consultation entity) {
        return false;
    }

    @Override
    public boolean update(Consultation entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Consultation> getAll() {
        return List.of();
    }
}
