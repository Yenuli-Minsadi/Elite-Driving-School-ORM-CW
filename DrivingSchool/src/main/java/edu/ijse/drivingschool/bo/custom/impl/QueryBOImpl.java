package edu.ijse.drivingschool.bo.custom.impl;

import edu.ijse.drivingschool.bo.custom.QueryBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dao.custom.QueryDAO;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class QueryBOImpl implements QueryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public int getStudentsRegisteredForAllCourses() throws Exception {
        return queryDAO.getStudentsRegisteredForAllCourses();
    }
}
