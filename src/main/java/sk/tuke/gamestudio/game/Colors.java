package sk.tuke.gamestudio.game;

public class Colors {
    public Colors(char getBoard) {
        setColorBoard(getBoard);
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private void setColorBoard(char getBoard)
    {
        switch (getBoard) {
            case '*' -> System.out.print(Colors.ANSI_RED + getBoard + " " + Colors.ANSI_RESET);
            case '&' -> System.out.print(Colors.ANSI_CYAN + getBoard + " " + Colors.ANSI_RESET);
            case '?' -> System.out.print(Colors.ANSI_BLUE + getBoard + " " + Colors.ANSI_RESET);
            case '$' -> System.out.print(Colors.ANSI_GREEN + getBoard + " " + Colors.ANSI_RESET);
            case '@' -> System.out.print(Colors.ANSI_PURPLE + getBoard + " " + Colors.ANSI_RESET);
            case '%' -> System.out.print(Colors.ANSI_YELLOW + getBoard + " " + Colors.ANSI_RESET);
            case '#', '.' -> System.out.print(Colors.ANSI_WHITE + getBoard + " " + Colors.ANSI_RESET);
            case '+' -> System.out.print(getBoard + " ");
        }
    }


}
