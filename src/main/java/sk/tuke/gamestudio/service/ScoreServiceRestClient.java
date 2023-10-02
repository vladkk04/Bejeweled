package sk.tuke.gamestudio.service;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;

import java.util.Collections;
import java.util.List;


public class ScoreServiceRestClient implements ScoreService
{
    private final String url = "http://localhost:8080/api/score";
    private RestTemplate restTemplate;
    public ScoreServiceRestClient()
    {

    }

    public ScoreServiceRestClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }

    @Override
    public List<Score> getAllUsers()
    {
        return Collections.singletonList(restTemplate.getForEntity(url + "/users", Score.class).getBody());
    }

    @Override
    public Score getUserById(long id)
    {
        return restTemplate.getForEntity(url + id, Score.class).getBody();
    }

    @Override
    public void reset()
    {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}