package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Consultation;
import edu.ijse.drivingschool.entity.Coordinator;

import java.util.List;

public interface CoordinatorDAO extends CrudDAO<Coordinator> {
    public boolean save(Coordinator entity);
    public boolean update(Coordinator entity);
    public boolean delete(String id);
    public List<Coordinator> getAll();
}
