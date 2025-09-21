package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Payment;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    public String getNextId() throws Exception;
    public boolean save(Payment entity);
    public boolean update(Payment entity);
    public boolean delete(String id);
    public List<Payment> getAll();
}
