package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Consultation;

import java.util.List;

public interface ConsultationDAO extends CrudDAO<Consultation> {
    public boolean save(Consultation entity);
    public boolean update(Consultation entity);
    public boolean delete(String id);
    public List<Consultation> getAll();
}
