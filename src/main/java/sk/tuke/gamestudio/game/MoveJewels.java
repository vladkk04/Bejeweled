package sk.tuke.gamestudio.game;

import java.util.Scanner;
public class MoveJewels
{
    private final Scanner scanner;
    private final Board board;
    private boolean writeJewel = false;

    char currentJewelPos = 0;
    char currentNextJewelPos = 0;

    int currentRow = 0;
    int currentColumn = 0;

    public MoveJewels(Board board)
    {
        scanner = new Scanner(System.in);
        this.board = board;
    }

    public void moveJewels(CheckJewels checkJewels, int inputFirstNumber, int inputSecondNumber)
    {

        if(!writeJewel)
        {
            currentRow = inputFirstNumber;
            currentColumn = inputSecondNumber;
            currentJewelPos = board.board[currentRow][currentColumn];
            writeJewel = true;
        }
        else if(inputFirstNumber  == currentRow && inputSecondNumber == currentColumn)
        {
            writeJewel = false;
        }
        else
        {
            currentNextJewelPos = board.board[inputFirstNumber][inputSecondNumber];

            if(currentRow > (inputFirstNumber + 1) || currentColumn > (inputSecondNumber + 1))
            {
                System.out.println("\nYou can't move to this\n");
            }
            else
            {
                board.board[currentRow][currentColumn] = currentNextJewelPos;
                board.board[inputFirstNumber][inputSecondNumber] = currentJewelPos;

                if(!checkJewels.checkForMatches())
                {
                    System.out.println("\nThese crystals cannot be connected to each other\n");
                    board.board[currentRow][currentColumn] = currentJewelPos;
                    board.board[inputFirstNumber][inputSecondNumber] = currentNextJewelPos;
                }
            }
            writeJewel = false;
        }
    }
}
