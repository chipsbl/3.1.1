package app.PP_311.dao;

import app.PP_311.model.User;
import java.util.List;

public interface UserDaoHibernate {
    void save(User user);

    void deleteById(Long id);

    void update(User user, Long id);

    List<User> getAll();

    User findById(Long id);
}
