package sk.tuke.gamestudio.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Player;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Integer> {
}

