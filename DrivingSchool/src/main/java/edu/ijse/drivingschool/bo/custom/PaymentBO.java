package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(PaymentDTO paymentDTO);
    public boolean update(PaymentDTO paymentDTO);
    public boolean delete(String id);
    public List<PaymentDTO> getAll();

}
