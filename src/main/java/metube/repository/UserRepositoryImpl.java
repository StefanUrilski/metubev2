package metube.repository;

import metube.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<User> findAll() {
        entityManager.getTransaction().begin();
        List<User> allUsers = entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return allUsers;
    }

    @Override
    public User findById(String id) {
        entityManager.getTransaction().begin();
        User user = entityManager
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public long size() {
        entityManager.getTransaction().begin();
        long size = entityManager
                .createQuery("SELECT count(u) FROM User u", long.class)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        entityManager.getTransaction().begin();
        User user;
        try {
            user = entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public User findByUsername(String username) {
        entityManager.getTransaction().begin();
        User user;
        try {
            user = entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username = :username ", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }
}
