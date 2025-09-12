package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.dao.custom.InstructorDAO;
import edu.ijse.drivingschool.entity.Instructor;

import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    @Override
    public boolean save(Instructor entity) {
        return false;
    }

    @Override
    public boolean update(Instructor entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Instructor> getAll() {
        return List.of();
    }
}
