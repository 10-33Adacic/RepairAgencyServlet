package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDTO;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory ;
    private UserDao userDao ;

    public UserService() {
        this.daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.createUserDao();
    }

    public Optional<List<User>> findAllUsers(int page, int size){
        return Optional.ofNullable(userDao.findAll(page,size));
    }

    public Optional<User> addUser(UserDTO user) throws SQLException {
        User newUser = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .active(true)
                .build();
        userDao.add(newUser);
        return Optional.ofNullable(newUser);
    }

    public Optional<User> findUser(String email, String password){
        Optional<User> user = Optional.ofNullable((userDao.findByEmail(email)));
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public Optional<User> findUserEmail(String email){
        return Optional.ofNullable((userDao.findByEmail(email)));
    }

    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(userDao.findByEmail(email));
    }

    public  Optional<User> findById(Long id){
        return Optional.ofNullable(userDao.findById(id));
    }

    public Optional<List<User>>findByRole(Integer role){
        return Optional.ofNullable(userDao.findByRole(role));
    }
}
