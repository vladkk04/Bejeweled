package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;


@Transactional
public class ScoreServiceJPA implements ScoreService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score)
    {
        entityManager.persist(score);
    }
    @Override
    public List<Score> getAllUsers()
    {
        return entityManager.createQuery("SELECT u FROM Score u").getResultList();
    }

    @Override
    public Score getUserById(long id)
    {
        return entityManager.find(Score.class, id);
    }

    @Override
    public void reset()
    {
        entityManager.createNativeQuery("DELETE FROM score").executeUpdate();
    }


}
