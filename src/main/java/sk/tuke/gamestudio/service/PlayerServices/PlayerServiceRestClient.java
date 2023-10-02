package sk.tuke.gamestudio.service.PlayerServices;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Player;

import java.util.Collections;
import java.util.List;


public class PlayerServiceRestClient implements PlayerService
{
    private final String url = "http://localhost:8080/api/player";
    private RestTemplate restTemplate;
    public PlayerServiceRestClient()
    {

    }

    public PlayerServiceRestClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addScore(Player player) {
        restTemplate.postForEntity(url, player, Player.class);
    }

    @Override
    public List<Player> getAllUsers()
    {
        return Collections.singletonList(restTemplate.getForEntity(url + "/allplayers", Player.class).getBody());
    }

    @Override
    public Player getUserById(long id)
    {
        return restTemplate.getForEntity(url + id, Player.class).getBody();
    }

    @Override
    public void update(Player player) {
        restTemplate.put(url + "/update", player, Player.class);
    }

}