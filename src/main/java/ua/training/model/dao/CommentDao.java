package ua.training.model.dao;

import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Comment;

import java.sql.Connection;
import java.sql.SQLException;

public interface CommentDao extends GenericDao<Comment>{
    default Connection receiveConnection(){

        try {
            return ConnectionPoolHolder.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
