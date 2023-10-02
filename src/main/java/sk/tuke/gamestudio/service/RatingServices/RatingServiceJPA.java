package sk.tuke.gamestudio.service.RatingServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRating(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public List<Rating> getAllRating()
    {
        return entityManager.createQuery("SELECT r FROM Rating r").getResultList();
    }

    @Override
    public Rating getRatingById(long id)
    {
        return entityManager.find(Rating.class, id);
    }
}
