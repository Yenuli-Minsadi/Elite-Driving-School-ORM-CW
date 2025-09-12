package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.dao.custom.ConsultationDAO;
import edu.ijse.drivingschool.dao.custom.LessonDAO;
import edu.ijse.drivingschool.entity.Lesson;

import java.util.List;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public boolean save(Lesson entity) {
        return false;
    }

    @Override
    public boolean update(Lesson entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Lesson> getAll() {
        return List.of();
    }
}
