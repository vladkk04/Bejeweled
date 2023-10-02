package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

public interface CommentService
{
    void addComment(Comment comment);

    List<Comment> getAllComments();

    Comment getCommentById(long id);
}
