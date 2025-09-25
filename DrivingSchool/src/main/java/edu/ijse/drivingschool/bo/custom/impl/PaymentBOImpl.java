package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.PaymentDAO;
import edu.ijse.drivingschool.dao.custom.RegistrationDAO;
import edu.ijse.drivingschool.dto.PaymentDTO;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Payment;
import edu.ijse.drivingschool.entity.Registration;
import edu.ijse.drivingschool.exception.DuplicateEntryException;
import edu.ijse.drivingschool.util.PaymentFieldsValidator;
import edu.ijse.drivingschool.util.StudentFieldsValidator;

import java.util.ArrayList;
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

        PaymentFieldsValidator.fieldsValidate(paymentDTO);

        if (paymentDAO.getById(paymentDTO.getPaymentId()) != null) {
            throw new DuplicateEntryException("Payment with ID " + paymentDTO.getPaymentId() + " already exists.");
        }

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
    public boolean update(PaymentDTO paymentDTO) throws Exception {
        Registration registration = registrationDAO.getById(paymentDTO.getRegistrationId());
        return paymentDAO.update(new Payment(
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
    public boolean delete(String id) {
        return paymentDAO.delete(id);
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<Payment> entity=paymentDAO.getAll();
        List<PaymentDTO>paymentDTO=new ArrayList<>();
        for( Payment payment :entity){
            paymentDTO.add(new PaymentDTO(
                    payment.getPaymentId(), payment.getRegistration(), payment.getPaymentType(), payment.getPaymentMethod(), payment.getPaymentAmount(),
                    payment.getPaymentDate(), payment.getStatus()));
        }
        return paymentDTO;
    }

    @Override
    public Payment getById(String paymentId) throws Exception {
        return paymentDAO.getById(paymentId);
    }
}
