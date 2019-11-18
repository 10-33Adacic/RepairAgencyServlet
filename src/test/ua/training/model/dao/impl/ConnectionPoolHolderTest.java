package ua.training.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ua.training.model.dao.CommentDao;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ConnectionPoolHolderTest {

    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @Spy
    private JDBCCommentDao commentDao = spy(JDBCCommentDao.class);

    @Before
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(commentDao.receiveConnection()).thenReturn(connection);
        when(resultSet.next()).thenReturn(false);
    }

    @Test
    public void getDataSource() {
//        try {
//            DataSource connection = ConnectionPoolHolder.getDataSource();
//            assertNotNull("Connection was not received", connection.getConnection());
//
//        } catch (Exception e) {
//            fail("Connection");
//
//        }
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            commentDao.findAll(1,10);
            verify(connection).close();
        } catch (Exception e) {
            fail("sql exception");
        }
    }
}
