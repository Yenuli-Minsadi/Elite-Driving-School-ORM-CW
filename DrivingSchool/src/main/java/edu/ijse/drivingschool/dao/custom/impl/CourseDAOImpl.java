package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.CourseDAO;
import edu.ijse.drivingschool.entity.Course;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Course entity) {
        return false;
    }

    @Override
    public boolean update(Course entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }
}
