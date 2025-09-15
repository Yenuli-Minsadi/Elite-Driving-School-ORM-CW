package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.ConsultationBO;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.CoordinatorDAO;
import edu.ijse.drivingschool.dao.custom.CourseDAO;
import edu.ijse.drivingschool.dto.CourseDTO;

import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean save(CourseDTO courseDTO) {
        return false;
    }

    @Override
    public boolean update(CourseDTO courseDTO) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<CourseDTO> getAll() {
        return List.of();
    }
}
