package ua.training.model.dao.impl;

import ua.training.model.dao.CommentDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Comment;

import java.sql.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCCommentDao implements CommentDao {
    private final ResourceBundle bundle = ResourceBundle.getBundle("queries");

    //TODO: delete this after removing to properties
//    private String queryAdd = "INSERT INTO comment ( comment, user_id, date) VALUES (?,?,?)";
//    private String queryFindAll = "SELECT * FROM comment LIMIT ?,? ";
//    private String queryCount = "SELECT COUNT(*) FROM comment";

    private Connection connection;
    private UserDao userDao;

    public JDBCCommentDao() {
    }

    JDBCCommentDao(Connection connection, UserDao userDao) {
        this.connection = connection;
        this.userDao = userDao;
    }

    public long findCount() {
        long count = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(bundle.getString("query.count.comment"))) {

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public void add(Comment entity) {
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.add.comment"))) {
            ps.setString(1, entity.getComment());
            ps.setLong(2, entity.getUser().getId());
            ps.setDate(3, Date.valueOf(LocalDate.now(Clock.system(ZoneId.of("Europe/Kiev")))));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Invalid input");
        }
    }

    @Override
    public Comment findByEmail(String email) {
        return null;
    }

    @Override
    public List<Comment> findAll(int page, int size) {
        List<Comment> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.all.comments"))) {
            ps.setInt(1, (page - 1) * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    private Comment extractFromResultSet(ResultSet rs)
            throws SQLException {
        return Comment.builder()
                .id(rs.getLong("id"))
                .comment(rs.getString("comment"))
                .date(rs.getDate("date").toLocalDate())
                .user(userDao.findById(rs.getLong("user_id")))
                .build();
    }
}
