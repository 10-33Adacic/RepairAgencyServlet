package ua.training.model.dao.impl;

import ua.training.model.dao.RequestDao;
import ua.training.model.dao.mapper.RequestMapper;
import ua.training.model.entity.Request;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCRequestDao implements RequestDao {
    private static RequestMapper requestMapper;
    private final ResourceBundle bundle = ResourceBundle.getBundle("queries");

    private Connection connection;

    JDBCRequestDao(Connection connection) {
        this.connection = connection;
        requestMapper = new RequestMapper();
    }

    @Override
    public void add(Request entity)  {
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.add.request"))) {
            ps.setString(1, entity.getRequest());
            ps.setString(2, entity.getStatus());
            ps.setInt(3, 0);
            ps.setString(4, entity.getCreator());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Invalid input");
        }
    }

    @Override
    public Request findByEmail(String email) {
        return null;
    }

    @Override
    public List<Request> findAll(int page, int size) {
        List<Request> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.all.request"))) {
            ps.setInt( 1, (page - 1) * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request result = requestMapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<Request> findByCreatorAndStatus(String creator, String status) {
        List<Request> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.creator.and.status.request"))) {
            ps.setString(1, creator);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(requestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<Request> findByCreatorAndNotStatus(String creator,
                                                   String status,
                                                   int page,
                                                   int size) {
        List<Request> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.creator.and.not.status.request"))) {
            ps.setString(1, creator);
            ps.setString(2, status);
            ps.setInt(3, (page - 1) * size);
            ps.setInt(4, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(requestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
    @Override
    public List<Request> findByMasterAndStatus(String master,
                                               String status,
                                               int page,
                                               int size) {
        List<Request> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.master.and.status.request"))) {
            ps.setString(1, master);
            ps.setString(2, status);
            ps.setInt(3, (page - 1) * size);
            ps.setInt(4, size);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultList.add(requestMapper.extractFromResultSet(rs));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<Request> findByStatus(String status, int page, int size) {
        List<Request> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.status.request"))) {
            ps.setString(1, status);
            ps.setInt(2, (page - 1) * size);
            ps.setInt(3, size);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(requestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void updateStatusAndPriceAndUser(Long id,
                                            String status,
                                            Long price,
                                            User user) {
        try (PreparedStatement ps = connection.prepareStatement(
                bundle.getString("query.update.status.and.price.and.master"))) {
            ps.setString(1, status);
            ps.setLong(2,price);
            ps.setLong(3,user.getId());
            ps.setLong(4,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatusAndReason(Long id, String status, String reason) {
        try (PreparedStatement ps = connection.prepareStatement(
                bundle.getString("query.update.status.and.reason"))) {
            ps.setString(1, status);
            ps.setString(2,reason);
            ps.setLong(3,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Request entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                bundle.getString("query.update.request"))) {
            ps.setString(1, entity.getStatus());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                bundle.getString("query.delete.by.id.request"))) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long findCount() {
        long count = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(bundle.getString("query.count.request")); ResultSet resultSet = pstmt.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
