package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "score")
public class Score implements Serializable
{
    @SequenceGenerator(
            name = "score_seq",
            sequenceName = "score_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "score_seq")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "score")
    private int score;

    @Column(name = "played_time")
    private int playedTime;

    @Column(name = "played_on")
    private LocalDateTime playedOn;

    public Score(){}

    public Score(String username, int score, int playedTime, LocalDateTime playedOn) {
        this.username = username;
        this.score = score;
        this.playedTime = playedTime;
        this.playedOn = playedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(int playedTime) {
        this.playedTime = playedTime;
    }

    public LocalDateTime getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(LocalDateTime playedOn) {
        this.playedOn = playedOn;
    }


}