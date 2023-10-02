package sk.tuke.gamestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Integer> {}
