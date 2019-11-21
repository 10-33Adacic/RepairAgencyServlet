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

public class JDBCUserDao implements UserDao {
    private static UserMapper mapper = new UserMapper();
    private final ResourceBundle bundle = ResourceBundle.getBundle("queries");

    //TODO: delete this after removing to properties
//    private String QUERY_ADD = "INSERT INTO user (email , password ,  active) VALUES (? ,? ,? )";
//    private String queryAddRole = "INSERT INTO  user_role(user_id,role_id) VALUES(?,?)";
//    private String queryFindByEmail = "SELECT id,email,password,active , role_id FROM user  INNER JOIN user_role ON user.id=user_role.user_id WHERE email = ?";
//    private String queryFindAll = "SELECT * FROM user";
    private String queryFindById = "SELECT * FROM user where id = ?";
//    private String queryUpdateUser = "UPDATE user SET email = ? , password = ?,  active = ? WHERE id = ?";
//    private String queryUpdateRole = "UPDATE user_role SET role_id=? WHERE user_id=?";
//    private String queryDeleteById = "DELETE FROM user WHERE id = ?";
//    private String queryCount = "SELECT COUNT(*) FROM request";

//    private String queryFindByRole = "SELECT id,email,password,active , role_id FROM reg_form.user  INNER JOIN reg_form.user_role ON user.id=user_role.user_id WHERE role_id = ?";

    private static final String QUERY_USER_FIND_BY_ID =
            "SELECT * FROM user WHERE id = ?";



    private Connection connection;

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(User entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.add.user"))) {
            //TODO: delete second try
            try (PreparedStatement ps1 = connection.prepareStatement(bundle.getString("query.add.role"))) {
//                connection.setAutoCommit(false);
                ps.setString(1, entity.getEmail());
                ps.setString(2, entity.getPassword());
                ps.setBoolean(3, entity.isActive());
                ps.executeUpdate();


/*
                //TODO:Delete this after test
                System.out.println("First sout" + currentUser.getEmail());
//                System.out.println("Second sout" + entity.getId());
                System.out.println("Third sout" + currentUser.getId());*/

               /* ps1.setLong(1, currentUser.getId());
                ps1.setLong(2, );
                ps1.executeUpdate();*/
                /*connection.commit();
                connection.setAutoCommit(true);*/
            }
        }
//
//        catch (SQLException e) {
//            throw new RuntimeException("Invalid input");
//        }
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.email"))) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractFromResultSet(rs);
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
                User result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                bundle.getString("query.update.user"))) {
            try (PreparedStatement ps1 = connection.prepareStatement(bundle.getString("query.update.role"))) {
                connection.setAutoCommit(false);
                ps.setString(1, entity.getEmail());
                ps.setString(2, entity.getPassword());
//                ps.setInt(3, Arrays.asList(Role.values()).indexOf(entity.getRole()));
                ps.setBoolean(3, entity.isActive());
                ps.setLong(4, entity.getId());
                ps.executeUpdate();

                ps1.setLong(1, Arrays.asList(Role.values()).indexOf(entity.getRole()) + 1);
                ps1.setLong(2, entity.getId());
                ps1.executeUpdate();

                connection.commit();
            }
        }
        catch (SQLException e) {
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

    private User extractFromResultSet(ResultSet rs)
            throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.values()[rs.getInt("role_id") - 1])
                .active(rs.getBoolean("active"))
                .build();
    }

    @Override
    public User findById(Long id) {
        try (PreparedStatement ps = connection.prepareStatement
                (bundle.getString("query.find.by.email"))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractFromResultSet(rs);
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
                User result = extractFromResultSet(rs);
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

        try (PreparedStatement pstmt = connection.prepareStatement(bundle.getString("query.count"))) {

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
}
