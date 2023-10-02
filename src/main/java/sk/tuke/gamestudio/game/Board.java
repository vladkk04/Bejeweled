package sk.tuke.gamestudio.game;

import java.util.Random;

public class Board
{
    private final Random random;
    private final int BOARD_SIZE;
    public final char[][] board;
    private static final char[] JEWELS = {'*', '?', '$', '@', '#', '%', '&', '+'};

    public Board(int sizeBoard)
    {
        this.BOARD_SIZE = sizeBoard;
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.random = new Random();
        initializeBoard();
    }

    public int randomize() {
        return random.nextInt(JEWELS.length);
    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public void initializeBoard()
    {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = JEWELS[randomize()];
            }
        }
    }

    public void refillBoard()
    {

        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = BOARD_SIZE - 1; j >= 0; j--)
            {
                if (board[j][i] == '.') {
                    int aboveRow = j - 1;
                    while (aboveRow >= 0) {
                        if (board[aboveRow][i] != '.') {
                            board[j][i] = board[aboveRow][i];
                            board[aboveRow][i] = '.';
                            break;
                        }
                        aboveRow--;
                    }
                    if (aboveRow == -1) {
                        board[j][i] = JEWELS[randomize()];
                    }
                }
            }
        }
        printBoard();
    }

    public void printBoard()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                new Colors(board[i][j]);
            }
            if(i == 0)
            {
                //System.out.println("    Player: " + player.getScore());
            }
            if(i == 1)
            {
                //System.out.println("    Time is remaining: " + Arrays.toString(bewejeledTimer.getTimeRemaining()));
            }
            else if(i > 1)
            {
                System.out.println();
            }
        }
        System.out.println();
    }
}
