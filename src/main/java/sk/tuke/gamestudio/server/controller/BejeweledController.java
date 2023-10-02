package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Player;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.game.BewejeledTimer;
import sk.tuke.gamestudio.game.Board;
import sk.tuke.gamestudio.game.CheckJewels;
import sk.tuke.gamestudio.game.MoveJewels;
import sk.tuke.gamestudio.service.CommentServices.CommentService;
import sk.tuke.gamestudio.service.PlayerServices.PlayerService;
import sk.tuke.gamestudio.service.RatingServices.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BejeweledController
{
    private Player player = new Player();
    private final Board board = new Board(8);
    private final MoveJewels moveJewels = new MoveJewels(board);
    private final CheckJewels checkJewels = new CheckJewels(board);
    private final RestTemplate restTemplate = new RestTemplate();
    private BewejeledTimer bewejeledTimer;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private PlayerService playerService;

    @Autowired
    private ScoreService scoreService;

    private Rating rating;
    private Comment comment;
    private boolean startTimer = false;
    private boolean createTimer = false;
    private boolean isWon = false;

    @RequestMapping("/bejeweled")
    public String bejeweled(Model model)
    {
        if(player.getUsername().equals(""))
        {
            player.setUsername("guess");
        }
        if(!createTimer)
        {
            bewejeledTimer = new BewejeledTimer(32);
            createTimer = true;
        }
        if(!isWon)
        {
            checkJewels.checkForMatches();
        }

        return "bejeweled";
    }

    @GetMapping("bejeweled/updateBoard")
    public String updateBoard(Model model)
    {
        if(!isWon)
        {
            checkJewels.checkForMatches();
        }

        return "bejeweled :: #board-container";
    }

    @GetMapping("bejeweled/guessPlayer")
    @ResponseBody
    public String guess(Model model)
    {
        return player.getUsername();
    }

    @GetMapping("/bejeweled/select-jewel")
    public String moveJewel(@RequestParam("row") int row, @RequestParam("column") int column)
    {
        if(bewejeledTimer.getTimeRemaining() <= 30 && !isWon)
        {
            moveJewels.moveJewels(checkJewels, row, column);
        }
        return "bejeweled";
    }

    @GetMapping("bejeweled/timer")
    @ResponseBody
    public int getTimeRemaining()
    {
        if(bewejeledTimer.getTimeRemaining() == 0 && !startTimer)
        {
            fillDatabasePlayer(this.player);
            startTimer = true;
            isWon = true;
        }

        return bewejeledTimer.getTimeRemaining();
    }

    @GetMapping("bejeweled/score")
    @ResponseBody
    public int getScoreGame()
    {
        return checkJewels.getFullScore();
    }

    @RequestMapping("/api/player/allplayers")
    @ResponseBody
    public String topPlayers(Model model)
    {
        return playerService.getAllUsers().toString();
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("player", player);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute Player player, Model model)
    {
        List<Player> players = playerService.getAllUsers();
        List<String> usernames = new ArrayList<>();
        List<String> passwords = new ArrayList<>();

        for(Player p : players) {
            usernames.add(p.getUsername());
            passwords.add(p.getPassword());
        }

        if(!usernames.contains(player.getUsername()) || !passwords.contains(player.getPassword()))
        {
            if(player.getUsername().equals(""))
            {
                this.player = player;
                checkJewels.fullScore = 0;
                bewejeledTimer = new BewejeledTimer(32);
                startTimer = false;
                createTimer = false;
                isWon = false;
                return "redirect:/bejeweled";
            }
            model.addAttribute("error","Incorrect");
            return "redirect:/login";
        }

        this.player = player;
        checkJewels.fullScore = 0;
        bewejeledTimer = new BewejeledTimer(32);
        startTimer = false;
        createTimer = false;
        isWon = false;

        return "redirect:/bejeweled";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("player", player);
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute Player player, Model model) throws RestClientException {
        List<Player> players = playerService.getAllUsers();
        List<String> usernames = new ArrayList<>();
        for(Player p : players) {
            usernames.add(p.getUsername());
        }

        if(usernames.contains(player.getUsername()))
        {
            model.addAttribute("error","Incorrect");
            return "redirect:/register";
        }

        this.player = player;
        this.player.setUsername(this.player.getUsername());
        this.player.setPassword(this.player.getPassword());
        restTemplate.put("http://localhost:8080/api/player/update", this.player);
        return "redirect:/login";
    }

    public void fillDatabasePlayer(Player player) throws RestClientException {
        boolean foundUsername = false;

        this.player = player;

        List<Player> players = playerService.getAllUsers();

        for (Player p : players)
        {
            if (p.getUsername().equals(player.getUsername()))
            {
                if(p.getScore() < checkJewels.getFullScore())
                {
                    p.setScore(checkJewels.getFullScore());
                }
                p.setPlayedOn(LocalDateTime.now());
                restTemplate.put("http://localhost:8080/api/player/update", p, Player.class);
                foundUsername = true;
                break;
            }
        }
        if(!foundUsername)
        {
            Player guessPlayer = new Player();
            guessPlayer.setUsername("Guess");
            guessPlayer.setScore(checkJewels.getFullScore());
            guessPlayer.setPlayedOn(LocalDateTime.now());
            restTemplate.put("http://localhost:8080/api/player/update", guessPlayer, Player.class);
        }
    }

    @GetMapping("bejeweled/comment")
    public String comment(Model model)
    {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "bejeweled::comment-container";
    }

    @PostMapping("bejeweled/comment")
    public String commentPost(@ModelAttribute Comment comment)
    {
        comment.setCommentedOn(LocalDateTime.now());
        comment.setUsername(player.getUsername());
        commentService.addComment(comment);
        return "redirect:/bejeweled";
    }

    @GetMapping("bejeweled/rating")
    public String rating(Model model)
    {
        List<Rating> ratings = ratingService.getAllRating();
        model.addAttribute("ratings", ratings);
        return "puzzle::rating-container";
    }

    @PostMapping("bejeweled/rating")
    public String ratingPost(@ModelAttribute Rating rating)
    {
        rating.setRatedOn(LocalDateTime.now());
        rating.setUsername(player.getUsername());
        ratingService.addRating(rating);
        return "redirect:/bejeweled";
    }

    public String getHtmlBoard()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<table id=\"board\">\n");
        for (int i = 0; i < board.getBOARD_SIZE(); i++) {
            sb.append("<tr>\n");
            for (int j = 0; j < board.getBOARD_SIZE(); j++) {
                sb.append("<td>\n");
                sb.append("<button class='jewel-button' id=row=").append(i).append("&column=").append(j).append(">\n");
                switch (board.board[i][j])
                {
                    case '*':
                        sb.append("<img src='/images/diamond.png'>");
                        break;
                    case '?':
                        sb.append("<img src='/images/emerald.png'>");
                        break;
                    case '$':
                        sb.append("<img src='/images/gem.png'>");
                        break;
                    case '@':
                        sb.append("<img src='/images/gemstone1.png'>");
                        break;
                    case '#':
                        sb.append("<img src='/images/gemstone2.png'>");
                        break;
                    case '%':
                        sb.append("<img src='/images/gemstone3.png'>");
                        break;
                    case '&':
                        sb.append("<img src='/images/tourmaline.png'>");
                        break;
                    case '+':
                        sb.append("<img src='/images/gemstone.png'>");
                        break;
                    case '.':
                        sb.append("<img src='/images/explosion.png'>");
                        break;
                }
                sb.append("</button>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

}
