package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.dao.custom.PaymentDAO;
import edu.ijse.drivingschool.dto.PaymentDTO;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean save(PaymentDTO paymentDTO) {
        return false;
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<PaymentDTO> getAll() {
        return List.of();
    }
}
