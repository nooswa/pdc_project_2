package wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Larissa Goh 18029695
 * This class records the players game stats such as number of games played, 
 * games won and the winning words in a file.
 */
public class Score { 
    
    private int gamesPlayed = 0;  
    private int gamesWon = 0;     
    private final List<String> winningWords = new ArrayList<>();  
    
// Record the current game stats into the file
    public void recordGame(GameLogic game) {
        readScore();
        gamesPlayed++;
        
        if (game.didWin()) {
            gamesWon++;
            winningWords.add(game.getSecretWord());
        }

        System.out.println("Games Played: " + gamesPlayed + ", Games Won: " + gamesWon);
        writeScore();
    }
    
// Reads the file to update games played, games won, and the list of winning words.
private void readScore() {
        BufferedReader br = null;

        try {
            // Initialize BufferedReader to read from History.txt
            br = new BufferedReader(new FileReader("./resources/History.txt"));
            String line;

            // Read and parse the first line for games played
            if ((line = br.readLine()) != null) {
                // Split the line by colon and parse the number of games played.
                String[] splitLine = line.split(":");
                if (splitLine.length > 1) {
                    try {
                        gamesPlayed = Integer.parseInt(splitLine[1].trim());
                    } catch (NumberFormatException e) {
                        Logger.getLogger(Score.class.getName()).log(Level.SEVERE, "Invalid number format in games played", e);
                    }
                }
            }
            // Read and parse the second line for games won
            if ((line = br.readLine()) != null) {
                // Split the line by colon and parse the number of games won.
                String[] splitLine = line.split(":");
                if (splitLine.length > 1) {
                    try {
                        gamesWon = Integer.parseInt(splitLine[1].trim());
                    } catch (NumberFormatException e) {
                        Logger.getLogger(Score.class.getName()).log(Level.SEVERE, "Invalid number format in games won", e);
                    }
                }
            }
            // Skips printing out "Winning Words:" header.
            if ((line = br.readLine()) != null) {
            // Read each line after the header and add it to the winning words list
            while ((line = br.readLine()) != null) {
            winningWords.add(line.trim());
         }
       }
            
        } catch (IOException e) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, "Error reading the file", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    Logger.getLogger(Score.class.getName()).log(Level.SEVERE, "Failed to close the BufferedReader", e);
                }
            }
        }
    }

// Logs games played, games won, and the winning wordsinto the History.txt file.
    private void writeScore() {
        try {
            
            try (FileWriter writer = new FileWriter("./resources/History.txt")) {
                writer.write(String.format("Games Played: %d\n", gamesPlayed));
                writer.write(String.format("Games Won: %d\n", gamesWon));
                writer.write("Winning Words:\n");
                
                for (String word : winningWords) {
                    writer.write(word + "\n");
                }
            }
        } catch (IOException e) {
              Logger.getLogger(Score.class.getName()).log(Level.SEVERE, "Error writing to file", e);
        }
    }
    
  
}

  
