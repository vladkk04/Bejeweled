package sk.tuke.gamestudio.service.SQLService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreService
{
    private final Connection connection;

    public ScoreService() throws SQLException
    {
        String url = "jdbc:postgresql://localhost:5432/BejeweledDatabase";
        String user = "postgres";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void addScore(String playerName, int score, int playerTime) throws SQLException
    {
        String sql = "INSERT INTO player_table (player_name, player_score, player_play_time) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, playerName);
        statement.setInt(2, score);
        statement.setInt(3, playerTime);
        statement.executeUpdate();
    }

    public void close() throws SQLException {
        connection.close();
    }
}