package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.ConsoleUI;
import sk.tuke.gamestudio.service.CommentServices.CommentService;
import sk.tuke.gamestudio.service.CommentServices.CommentServiceRestClient;
import sk.tuke.gamestudio.service.PlayerServices.PlayerService;
import sk.tuke.gamestudio.service.PlayerServices.PlayerServiceRestClient;
import sk.tuke.gamestudio.service.RatingServices.RatingService;
import sk.tuke.gamestudio.service.RatingServices.RatingServiceRestClient;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceRestClient;

@SpringBootApplication
@Configuration
@EntityScan({"sk.tuke.gamestudio.entity"})
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }
    @Bean
    public CommandLineRunner runner(ConsoleUI ui) {
        return args -> ui.start();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PlayerService playerService(RestTemplate restTemplate) {
        return new PlayerServiceRestClient(restTemplate);
    }
    @Bean
    public ScoreService scoreService(RestTemplate restTemplate) {
        return new ScoreServiceRestClient(restTemplate);
    }

    @Bean
    public RatingService ratingService(RestTemplate restTemplate) {
        return new RatingServiceRestClient(restTemplate);
    }

    @Bean
    public CommentService commentService(RestTemplate restTemplate) {
        return new CommentServiceRestClient(restTemplate);}
}
