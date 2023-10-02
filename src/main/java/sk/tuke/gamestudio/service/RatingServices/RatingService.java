package sk.tuke.gamestudio.service.RatingServices;

import sk.tuke.gamestudio.entity.Rating;

import java.util.List;


public interface RatingService
{
    void addRating(Rating rating);

    List<Rating> getAllRating();

    Rating getRatingById(long id);
}
