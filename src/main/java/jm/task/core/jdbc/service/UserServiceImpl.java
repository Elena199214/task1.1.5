package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDao.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных ", name);
    }

    public void removeUserById(long id) throws SQLException {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = userDao.getAllUsers();
        list.forEach(System.out::println);
        return list;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
