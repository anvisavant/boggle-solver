import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;

public class BoggleCheat {
    public static void main(String[] args) {
        // Initialize the dictionary
        String dictionaryPath = new File("").getAbsolutePath() + File.separator + "dictionary.txt";
        DictionaryTree dictionary = loadDictionary(dictionaryPath);

        // Generate the Boggle board
        BoggleBoard board = new BoggleBoard(4);
        board.printBoard();

        // Solve the Boggle board
        BoggleSolver solver = new BoggleSolver(board.getBoard(), dictionary);
        Set<String> words = solver.findAllWords();

        // Print and score the found words
        System.out.println("\nFound words:");
        int totalScore = 0;
        for (String word : new TreeSet<>(words)) {
            int score = scoreWord(word);
            totalScore += score;
            System.out.printf("%s (%d points)\n", word, score);
        }
        System.out.printf("\nTotal score: %d points\n", totalScore);
    }

    private static DictionaryTree loadDictionary(String filename) {
        DictionaryTree dictionary = new DictionaryTree();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.insert(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
        return dictionary;
    }

    private static int scoreWord(String word) {
        int length = word.length();
        if (length < 3) return 0;
        if (length <= 4) return 1;
        if (length == 5) return 2;
        if (length == 6) return 3;
        if (length == 7) return 5;
        return 11;
    }
}
