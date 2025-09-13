package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.PaymentDAO;
import edu.ijse.drivingschool.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Payment entity) {
        return false;
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }
}
