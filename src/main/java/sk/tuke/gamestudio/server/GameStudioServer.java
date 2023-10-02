package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.service.CommentServices.CommentService;
import sk.tuke.gamestudio.service.CommentServices.CommentServiceJPA;
import sk.tuke.gamestudio.service.PlayerServices.PlayerService;
import sk.tuke.gamestudio.service.PlayerServices.PlayerServiceJPA;
import sk.tuke.gamestudio.service.RatingServices.RatingService;
import sk.tuke.gamestudio.service.RatingServices.RatingServiceJPA;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJPA;

@SpringBootApplication
@Configuration
@EntityScan({"sk.tuke.gamestudio.entity"})
public class GameStudioServer {
    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class);
    }

    @Bean
    public PlayerService playerService() {
        return new PlayerServiceJPA();
    }

    @Bean
    public CommentService commentService(){return new CommentServiceJPA();}

    @Bean
    public ScoreService scoreService(){return new ScoreServiceJPA();}

    @Bean
    public RatingService ratingService(){return new RatingServiceJPA();}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
