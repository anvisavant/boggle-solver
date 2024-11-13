import java.util.Random;

public class BoggleBoard {
    private static final String[] BOGGLE_DICE = {
        "AAEEGN", "ABBJOO", "ACHOPS", "AFFKPS",
        "AOOTTW", "CIMOTU", "DEILRX", "DELRVY",
        "DISTTY", "EEGHNW", "EEINSU", "EHRTVW",
        "EIOSST", "ELRTTY", "HIMNQU", "HLNNRZ"
    };
    
    private char[][] board;
    
    public BoggleBoard(int size) {
        board = new char[size][size];
        generateBoard();
    }
    
    private void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String die = BOGGLE_DICE[random.nextInt(BOGGLE_DICE.length)];
                board[i][j] = die.charAt(random.nextInt(die.length()));
            }
        }
    }
    
    public char[][] getBoard() {
        return board;
    }
    
    public void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
