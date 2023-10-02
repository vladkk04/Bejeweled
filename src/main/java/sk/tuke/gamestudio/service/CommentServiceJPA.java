package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(Comment comment)
    {
        entityManager.persist(comment);
    }

    @Override
    public List getAllComments()
    {
        return entityManager.createQuery("SELECT c FROM Comment c").getResultList();
    }

    @Override
    public Comment getCommentById(long id)
    {
        return entityManager.find(Comment.class, id);
    }
}
