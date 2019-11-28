package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCUserDao implements UserDao {
    private static UserMapper mapper;
    private final ResourceBundle bundle = ResourceBundle.getBundle("queries");

    private Connection connection;

    JDBCUserDao(Connection connection) {
        this.connection = connection;
        mapper = new UserMapper();
    }

    @Override
    public void add(User entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.add.user"))) {
                connection.setAutoCommit(false);
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getEmail());
                ps.setString(3, entity.getPassword());
                ps.setString(4, entity.getRole().name());
                ps.setBoolean(5, entity.isActive());

                ps.executeUpdate();
                connection.setAutoCommit(true);
        }

        catch (SQLException e) {
            throw new RuntimeException("Invalid input");
        }
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.email"))) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(bundle.getString("query.find.all"));

            while (rs.next()) {
                User result = mapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             bundle.getString("query.update.role"))
        ) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole().name());
            ps.setBoolean(5, entity.isActive());
            ps.setLong(6, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                bundle.getString("query.delete.by.id"))) {
            ps.setInt(1, id);
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
    public User findById(Long id) {
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.email"))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findByRole(Integer role) {
        List<User> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.role"))) {
            ps.setLong(1, role);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User result = mapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public long findCount() {
        long count = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(bundle.getString("query.count")); ResultSet resultSet = pstmt.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public User findByEmailAndPassword (String email, String password) {
        try (PreparedStatement ps =
                     connection.prepareStatement(bundle.getString("query.find.by.email.and.password"))
        ) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<User> findAllMasters() {
        List<User> resultList = new CopyOnWriteArrayList<>();

        try (PreparedStatement ps =
                     connection.prepareStatement(bundle.getString("query.find.all.masters"))
        ) {
            ps.setString(1, "MASTER");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User result = mapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
}
