package edu.ijse.drivingschool.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public String getNextId() throws Exception;
    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(String id);
    List<T> getAll();

}
