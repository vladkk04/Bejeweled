package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceRest
{
    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public void addScore(@RequestBody Score score)
    {
        scoreService.addScore(score);
    }

    @GetMapping("id/{id}")
    public Score getUsersById(@PathVariable long id)
    {
        return scoreService.getUserById(id);
    }

    @GetMapping("/users")
    public List<Score> getAllUsers()
    {
        return scoreService.getAllUsers();
    }
}
