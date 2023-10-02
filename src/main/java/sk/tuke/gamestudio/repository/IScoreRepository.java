package sk.tuke.gamestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Score;

@Repository
public interface IScoreRepository extends JpaRepository<Score, Integer> { }

