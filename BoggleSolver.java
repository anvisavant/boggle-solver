import java.util.*;

public class BoggleSolver {
    private static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private char[][] board;
    private boolean[][] visited;
    private DictionaryTree dictionary;
    private Set<String> foundWords;

    public BoggleSolver(char[][] board, DictionaryTree dictionary) {
        this.board = board;
        this.dictionary = dictionary;
        this.visited = new boolean[board.length][board[0].length];
        this.foundWords = new HashSet<>();
    }

    public Set<String> findAllWords() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, new StringBuilder());
            }
        }
        return foundWords;
    }

    private void dfs(int x, int y, StringBuilder currentWord) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
            return;
        }

        currentWord.append(board[x][y]);
        String word = currentWord.toString();

        if (!dictionary.startsWith(word)) {
            currentWord.setLength(currentWord.length() - 1);
            return;
        }

        if (word.length() >= 3 && dictionary.search(word)) {
            foundWords.add(word);
        }

        visited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            dfs(newX, newY, currentWord);
        }

        visited[x][y] = false;
        currentWord.setLength(currentWord.length() - 1);
    }
}
