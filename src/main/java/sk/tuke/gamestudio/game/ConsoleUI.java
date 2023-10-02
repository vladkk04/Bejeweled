package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Player;
import sk.tuke.gamestudio.service.CommentServices.CommentService;
import sk.tuke.gamestudio.service.PlayerServices.PlayerService;
import sk.tuke.gamestudio.service.RatingServices.RatingService;

import java.util.Scanner;

@Component
public class ConsoleUI
{
    @Autowired
    private PlayerService playerService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    public void start()
    {
        //Services
        /*---------------------------------*/
        Player player = new Player();
        /*---------------------------------*/

        String userName = SetUsername();

        BewejeledTimer bewejeledTimer = new BewejeledTimer(10);

        Board board = new Board(8);

        CheckJewels checkJewels = new CheckJewels(board);

        MoveJewels moveJewels = new MoveJewels(board);

        /*while (bewejeledTimer.getTimeRemaining()[0] > 0)
        {
            board.printBoard();

            while (checkJewels.checkForMatches()) {
                bewejeledTimer.addTimeRemaining(5);
            }

            //moveJewels.moveJewels(checkJewels,1,1);
        }*/

        //playerService.addScore(new Player(userName, player.getScore(), bewejeledTimer.getTotalTime(), LocalDateTime.now()));
        //commentService.addComment(new Comment(userName, SetComment(), new Date()));
        //ratingService.addRating(new Rating(userName, SetRating(), LocalDateTime.now()));

        System.exit(999);
    }

    private String SetUsername()
    {
        Scanner scannerPlayerName = new Scanner(System.in);
        System.out.println("Your Name: ");
        return scannerPlayerName.nextLine();
    }

    private String SetComment()
    {
        Scanner scannerComment = new Scanner(System.in);
        System.out.println("Leave a comment");
        return scannerComment.nextLine();
    }

    private int SetRating()
    {
        Scanner scannerRating = new Scanner(System.in);
        System.out.println("Leave a rating from 0 to 10");
        int assessment = scannerRating.nextInt();

        if(assessment > 10)
        {
            assessment = 10;
        }
        else if(assessment < 0)
        {
            assessment = 0;
        }
        return assessment;
    }
}
