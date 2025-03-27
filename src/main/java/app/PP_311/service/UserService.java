package app.PP_311.service;

import app.PP_311.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void delete(int id);

    void update(User user, int id);

    List<User> getAll();

    User findById(int id);
}
