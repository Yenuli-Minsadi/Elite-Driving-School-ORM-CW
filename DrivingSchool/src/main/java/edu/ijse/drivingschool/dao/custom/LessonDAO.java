package edu.ijse.drivingschool.dao.custom;

import edu.ijse.drivingschool.dao.CrudDAO;
import edu.ijse.drivingschool.entity.Lesson;

import java.util.List;

public interface LessonDAO extends CrudDAO<Lesson> {
    public String getNextId() throws Exception;
    public boolean save(Lesson entity);
    public boolean update(Lesson entity);
    public boolean delete(String id);
    public List<Lesson> getAll();
}
