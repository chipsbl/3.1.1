package app.PP_311.service;



import app.PP_311.dao.UserDaoHibernate;
import app.PP_311.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDaoHibernate userDao;

    @Autowired
    public UserServiceImpl(UserDaoHibernate userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user, int id) {
        User u = userDao.findById(id);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setAge(user.getAge());
        userDao.save(u);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }
}
