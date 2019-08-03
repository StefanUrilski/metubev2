package metube.repository;

import metube.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Tube> findAll() {
        entityManager.getTransaction().begin();
        List<Tube> allTubes = entityManager
                .createQuery("select t from Tube t", Tube.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return allTubes;
    }

    @Override
    public Tube findById(String id) {
        entityManager.getTransaction().begin();
        Tube tube = entityManager
                .createQuery("select t from Tube t WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public long size() {
        entityManager.getTransaction().begin();
        long size = entityManager
                .createQuery("select count(t) from Tube t", long.class)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public Tube update(Tube tube) {
        entityManager.getTransaction().begin();
        entityManager.merge(tube);
        entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public Tube findByYouTubeId(String youtubeId) {
        entityManager.getTransaction().begin();
        Tube tube = entityManager.createQuery("" +
                "select t " +
                "from Tube as t " +
                "where t.youtubeId = ?1", Tube.class)
                .setParameter(1, youtubeId)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public List<Tube> findAllByUsername(String username) {
        entityManager.getTransaction().begin();
        List<Tube> allTubes = entityManager
                .createQuery("" +
                        "select t " +
                        "from Tube t " +
                        "where t.uploader.username = ?1", Tube.class)
                .setParameter(1, username)
                .getResultList();
        entityManager.getTransaction().commit();

        return allTubes;
    }
}
