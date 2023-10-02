package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentServices.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentServiceRest
{
    @Autowired
    private CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody Comment comment)
    {
        commentService.addComment(comment);
    }
    @GetMapping("id/{id}")
    public Comment getCommentById(@PathVariable long id)
    {
        return commentService.getCommentById(id);
    }

    @GetMapping("/allcomments")
    public List<Comment> getAllComments()
    {
        return commentService.getAllComments();
    }
}
