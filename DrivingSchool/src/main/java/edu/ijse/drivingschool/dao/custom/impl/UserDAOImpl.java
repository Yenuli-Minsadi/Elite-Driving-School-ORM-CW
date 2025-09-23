package edu.ijse.drivingschool.dao.custom.impl;

import edu.ijse.drivingschool.config.FactoryConfiguration;
import edu.ijse.drivingschool.dao.custom.UserDAO;
import edu.ijse.drivingschool.entity.Student;
import edu.ijse.drivingschool.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws Exception {

        Session session = factoryConfiguration.getSession();

        String nextId = null;
        try {
            String lastId = session.createQuery
                            ("SELECT u.userId FROM User u ORDER BY u.userId DESC", String.class)
                    .setMaxResults(1).uniqueResult();

            nextId = (lastId == null) ? "U001" : String.format("U%03d", Integer.parseInt(lastId.substring(1))+1);

//        } catch (NullPointerException e) { //a null pointer exception since no ids found
//            e.printStackTrace();
        } finally {
            session.close();
        }
        return nextId;
    }


    @Override
    public boolean save(User entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            User existsUser = session.get(User.class, entity.getUserId());
            if(existsUser != null){
                throw new Exception("User already exists");
            }
            session.persist(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean update(User entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User existUser = session.get(User.class, entity.getUserId());

            if (existUser == null) {
                throw new Exception("User doesn't exist, User ID not found!");
            }
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = session.get(User.class,id);
            if (user == null) {
                throw new Exception("Cannot delete user, User ID not found!");
            }
            session.remove(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public User verifyUsername(String username) {
//        Session session = null;
        Session session = factoryConfiguration.getSession();
        try {
            Query <User> query = session.createQuery("FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username",username);
            return query.uniqueResult();//returns null if not found
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM User", User.class).list();
        } finally {
            session.close();
        }
    }
}
