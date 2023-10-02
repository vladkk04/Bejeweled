package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Player;
import sk.tuke.gamestudio.service.PlayerServices.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerServiceRest
{
    @Autowired
    private PlayerService playerService;

    @PostMapping
    public void addScore(@RequestBody Player player)
    {
        playerService.addScore(player);
    }
    @GetMapping("id/{id}")
    public Player getUsersById(@PathVariable long id)
    {
        return playerService.getUserById(id);
    }

    @GetMapping("/allplayers")
    public List<Player> getAllUsers()
    {
        return playerService.getAllUsers();
    }

    @PutMapping("/update")
    public void update(@RequestBody Player player){
        this.playerService.update(player);
    }
}
