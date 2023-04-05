package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

            try {
                UserServiceImpl userServiceImpl = new UserServiceImpl();
                userServiceImpl.createUsersTable();
                userServiceImpl.saveUser("Name1", "LastName1", (byte) 20);
                userServiceImpl.saveUser("Name2", "LastName2", (byte) 25);
                userServiceImpl.saveUser("Name3", "LastName3", (byte) 31);
                userServiceImpl.saveUser("Name4", "LastName4", (byte) 38);
                System.out.println(userServiceImpl.getAllUsers());
                userServiceImpl.cleanUsersTable();
                userServiceImpl.dropUsersTable();
            } finally {
                Util.closeFactory();
            }
        }
    }



