package ua.training.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import ua.training.model.dao.CommentDao;
import ua.training.model.entity.Comment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


public class JDBCCommentDaoTest {

    private CommentDao commentDao;
    private Connection connection;

    @Before
    public void setUp() {
        try {
            connection = ConnectionPoolHolder.getDataSource().getConnection();
            commentDao = new JDBCCommentDao(connection, new JDBCUserDao(connection));
        } catch (SQLException e) {
            fail("SQL exception( invalid connection");
        }
    }

    @Test
    public void testIfConnectionNotNull() {
        assertNotNull(connection);
    }

    @Test
    public void testIfDAONotNull() {
        assertNotNull(commentDao);
    }

    @Test
    public void findAll() {
        List<Comment> test = commentDao.findAll(1,10);
        assertNotNull("Empty comment",test.get(test.size() - 1).getComment() );
        assertNotNull("Empty date",test.get(test.size() - 1).getDate());
        assertNotNull("Empty comment id",test.get(test.size() - 1).getId());

    }
}
