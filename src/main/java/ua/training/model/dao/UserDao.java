package ua.training.model.dao;

import ua.training.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findById(Long id);
    List<User> findByRole(Integer role);
}
