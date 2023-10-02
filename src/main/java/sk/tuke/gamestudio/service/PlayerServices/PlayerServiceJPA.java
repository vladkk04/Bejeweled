package sk.tuke.gamestudio.service.PlayerServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Player;

import java.util.List;


@Transactional
public class PlayerServiceJPA implements PlayerService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Player player)
    {
        entityManager.persist(player);
    }
    @Override
    public List<Player> getAllUsers()
    {
        return entityManager.createQuery("SELECT u FROM Player u ORDER BY u.score DESC").setMaxResults(10).getResultList();
    }

    @Override
    public Player getUserById(long id)
    {
        return entityManager.find(Player.class, id);
    }

    @Override
    public void update(Player player) {
        entityManager.merge(player);
    }
}



