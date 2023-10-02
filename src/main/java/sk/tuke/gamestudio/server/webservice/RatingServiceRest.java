package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingServices.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest
{
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public void addScore(@RequestBody Rating rating)
    {
        ratingService.addRating(rating);
    }

    @GetMapping("id/{id}")
    public Rating getUsersById(@PathVariable long id)
    {
        return ratingService.getRatingById(id);
    }

    @GetMapping("/allratings")
    public List<Rating> getAllUsers()
    {
        return ratingService.getAllRating();
    }
}
