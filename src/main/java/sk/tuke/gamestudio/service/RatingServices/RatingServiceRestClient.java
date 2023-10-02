package sk.tuke.gamestudio.service.RatingServices;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Collections;
import java.util.List;

public class RatingServiceRestClient implements RatingService
{
    private final String url = "http://localhost:8080/api/rating";
    private RestTemplate restTemplate;

    public RatingServiceRestClient(){}

    public RatingServiceRestClient(RestTemplate restTemplate){this.restTemplate = restTemplate;}
    @Override
    public void addRating(Rating rating) {
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public List<Rating> getAllRating() {
        return Collections.singletonList(restTemplate.getForEntity(url + "/allrating", Rating.class).getBody());
    }

    @Override
    public Rating getRatingById(long id)
    {
        return restTemplate.getForEntity(url + id, Rating.class).getBody();
    }
}
