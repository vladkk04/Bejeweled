package sk.tuke.gamestudio.game;

public class CheckJewels
{
    private final Board board;
    private boolean hasMatch;
    private static final int MIN_MATCH = 3;
    public int fullScore = 0;
    public final int[] SCORES = {10, 25, 50, 75, 100, 200, 500, 1000};

    public int getFullScore()
    {
        return fullScore;
    }

    public CheckJewels(Board board)
    {
        this.board = board;
    }
    public boolean checkForMatches()
    {
        hasMatch = false;

        int BOARD_SIZE = board.getBOARD_SIZE();

        char[][] currentBoard = board.board;

        checkMatchesInDirection(currentBoard, BOARD_SIZE, true); // Check horizontal matches
        checkMatchesInDirection(currentBoard, BOARD_SIZE, false); // Check vertical matches

        if(hasMatch)
        {
            board.refillBoard();
        }

        return hasMatch;
    }

    private void checkMatchesInDirection(char[][] currentBoard, int BOARD_SIZE, boolean isHorizontal)
    {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE - MIN_MATCH + 1; j++) {
                char jewel = isHorizontal ? currentBoard[i][j] : currentBoard[j][i];
                if (jewel == '.') continue;
                int count = 1;
                for (int k = j + 1; k < BOARD_SIZE; k++) {
                    char compareJewel = isHorizontal ? currentBoard[i][k] : currentBoard[k][i];
                    if (compareJewel == jewel) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count >= MIN_MATCH) {
                    hasMatch = true;
                    int matchScore = SCORES[count - MIN_MATCH];
                    fullScore = fullScore + matchScore;
                    for (int k = j; k < j + count; k++) {
                        if (isHorizontal) {
                            currentBoard[i][k] = '.';
                        } else {
                            currentBoard[k][i] = '.';
                        }
                    }
                }
            }
        }
    }

}
