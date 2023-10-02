package sk.tuke.gamestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Rating;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Integer> { }

