package sk.tuke.gamestudio.service.CommentServices;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;

import java.util.Collections;
import java.util.List;


public class CommentServiceRestClient implements CommentService
{
    private final String url = "http://localhost:8080/api/comment";
    private RestTemplate restTemplate;
    public CommentServiceRestClient() {}

    public CommentServiceRestClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addComment(Comment comment) {
        restTemplate.postForEntity(url, comment, Comment.class);
    }

    @Override
    public List<Comment> getAllComments()
    {
        return Collections.singletonList(restTemplate.getForEntity(url + "/allcomments", Comment.class).getBody());
    }

    @Override
    public Comment getCommentById(long id)
    {
        return restTemplate.getForEntity(url + id, Comment.class).getBody();
    }
}