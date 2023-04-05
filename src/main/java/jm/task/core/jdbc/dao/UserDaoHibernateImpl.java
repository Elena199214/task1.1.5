package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            Query query = session.createSQLQuery("create table if not exists user " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastname VARCHAR(30), age TINYINT);")
                    .addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            Query query = session.createSQLQuery("DROP TABLE IF EXISTS user;")
                    .addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }
     catch (Exception e) {
         assert transaction != null;
         transaction.rollback();
        e.printStackTrace();
    }
}

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User where id = id").executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        }

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
