package app.PP_311.service;



import app.PP_311.dao.UserDaoHibernate;
import app.PP_311.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user, Long id) {
        userDao.update(user, id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}