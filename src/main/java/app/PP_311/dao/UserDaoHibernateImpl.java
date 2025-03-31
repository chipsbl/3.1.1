package app.PP_311.dao;

import app.PP_311.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
    public void deleteById(Long id) {
        User u = findById(id);
        em.remove(u);
    }

    @Override
    public void update(User user, Long id) {
        User u = findById(id);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setAge(user.getAge());
        em.merge(u);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT p FROM User p", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        if (em.find(User.class, id) == null) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        return em.find(User.class, id);
    }
}
