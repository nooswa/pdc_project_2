/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.model.SingleBox;
import DataBase.WordsDB;
import java.util.List;
/**
 *
 * @author Noor Swadi 22167422
 * This interface checks wether a letter of a word is in the valid words list or matches the secret word
 * referencing a wordsDB instance.
 */
public interface Enter{
    
    static int submit(SingleBox [] box_line, WordsDB words){
        int isWin = 0;  // Return win or not - in validwordlist or not
        int cnt = 0;        // Correct letter 

        String answer = words.getSecretWord();
        SingleBox box = null;
        String tobeCheck = new String("");  // Store current string for checkin

        for (SingleBox singleBox : box_line) {
            box = singleBox;
            tobeCheck = tobeCheck.concat(box.getText());
        }
        // Get the valid words list
        List<String> validWords = words.getValidWords();
        
        if (validWords.contains(tobeCheck.toString())){  // Check whether word is valid
            for (int i = 0; i < box_line.length; i++){
                box = box_line[i];
                char current = box.getText().charAt(0); // Current letter in the box
                int idx = answer.indexOf(current);      // Index of box letter in answer
                if (answer.indexOf(current) != -1){    
                    if (idx == i){      // Correct
                        cnt ++;
                        box.refresh(3);
                    }else{
                        box.refresh(2);
                    }
                    char [] chars = answer.toCharArray();
                    chars[idx] = '?';
                    answer = new String(chars);

                }else{  // Not found in answer
                    box.refresh(1);
                }

            }
            if (cnt == 5){
                isWin = 1;
            }
        }else{      // The word isnt in the wordlist
            isWin = 2;
            System.out.println("Word not in wordlist!");
        }

        return isWin;
    }

}
