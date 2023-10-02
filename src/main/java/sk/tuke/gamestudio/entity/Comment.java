package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")

public class Comment implements Serializable
{
    @SequenceGenerator(
            name = "comment_seq",
            sequenceName = "comment_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    private int id;

    public Comment(String username, String comment, LocalDateTime commentedOn)
    {
        this.username = username;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    @Column(name = "username")
    private String username;

    @Column(name = "comment")
    private String comment;

    @Column(name = "commented_on")
    private LocalDateTime commentedOn;

    public Comment() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(LocalDateTime commentedOn) {
        this.commentedOn = commentedOn;
    }


}
