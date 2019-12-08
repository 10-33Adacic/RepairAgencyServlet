package ua.training.model.dao.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUserDaoTest {

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    private static final String URL = "jdbc:mysql://localhost:3306/rep_service?useTimezone=true&serverTimezone=UTC";

    private Connection connection;

    private UserDao userDao;

    @Before
    public void before() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            userDao = new JDBCUserDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser() {
        try {
            final String u = "testUser1@email.com";
            final String p = "";
            final String n = "testUserName1";
            final User user = User.builder()
                    .email(u)
                    .password(p)
                    .role(Role.USER)
                    .active(true)
                    .name(n)
                    .build();

            userDao.add(user);

            User expected = userDao.findByEmailAndPassword(u, p);

            userDao.delete(expected.getId());

            Assert.assertNotNull(expected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
