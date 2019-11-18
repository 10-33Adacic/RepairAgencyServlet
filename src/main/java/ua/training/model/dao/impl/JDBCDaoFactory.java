package ua.training.model.dao.impl;

import ua.training.model.dao.CommentDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RequestDao;
import ua.training.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();


    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public RequestDao createRequestDao() {
        return new JDBCRequestDao(getConnection());
    }

    @Override
    public CommentDao createCommentDao() {
        return new JDBCCommentDao(getConnection(), createUserDao());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
