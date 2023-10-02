package sk.tuke.gamestudio.service.PlayerServices;

import sk.tuke.gamestudio.entity.Player;

import java.util.List;

public interface PlayerService
{
    void addScore(Player player);

    List<Player> getAllUsers();

    Player getUserById(long id);
    void update(Player player);
}
