package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.PaymentDAO;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.dto.PaymentDTO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Payment;
import edu.ijse.drivingschool.entity.Registration;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);

    @Override
    public String getNextId() throws Exception {
        return paymentDAO.getNextId();
    }

    @Override
    public boolean save(PaymentDTO paymentDTO) throws Exception{

        Registration registration = registrationDAO.getById(paymentDTO.getRegistrationId());

        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                registration,
                paymentDTO.getPaymentType(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getPaymentAmount(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getStatus()
        ));
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
