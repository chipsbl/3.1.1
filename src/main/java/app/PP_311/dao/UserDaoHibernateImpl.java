package app.PP_311.dao;

import app.PP_311.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl implements UserDaoHibernate {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) {
        em.merge(user);
    }

    @Override
    public void deleteById(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT p FROM User p", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }
}
