package sk.tuke.gamestudio.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
public class Rating implements Serializable
{

    @SequenceGenerator(
            name = "rating_seq",
            sequenceName = "rating_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "assessment")
    private int assessment;

    @Column(name = "rated_on")
    private LocalDateTime ratedOn;

    public Rating(){}

    public Rating(String username, int assessment, LocalDateTime ratedOn)
    {
        this.username = username;
        this.assessment = assessment;
        this.ratedOn = ratedOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public LocalDateTime getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(LocalDateTime ratedOn) {
        this.ratedOn = ratedOn;
    }


}
