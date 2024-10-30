/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.model;

import GUI.UI.SingleBox;
import GUI.UI.Position;
import javax.swing.*;
import java.awt.*;

/**
 *
 * LetterBox panel for arranging SingleBox components in a grid format.
 * 
 * @author Noor Swadi 22167422
 */
public class LetterBox extends JPanel {

    private final static SingleBox[][] boxes = new SingleBox[6][5];
    public static final int WIDTH = 335;
    public static final int HEIGHT = 403;
    public static final int ROWS = 6;
    public static final int COLS = 5;

    public LetterBox() {
        this.setFocusable(true); // Ensure LetterBox can receive focus
        this.setLayout(new GridLayout(ROWS, COLS, 5, 5));
        this.setSize(WIDTH, HEIGHT);

        // Initialize and add each SingleBox to the LetterBox panel
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = new SingleBox();
                super.add(boxes[i][j]);
            }
        }
    }

    // Clears the specified row in the LetterBox
    public void clearCurrentRow(int row) {
        for (SingleBox box : boxes[row]) {
            box.reset();
        }
        Position.setCol(0);
    }

    // Returns the specified row of SingleBox objects
    public SingleBox[] getRow(int row) {
        return this.boxes[row];
    }

    // Returns the SingleBox at the specified row and column
    public SingleBox getSingleBox(int row, int col) {
        return this.boxes[row][col];
    }

    // Restart game and refresh LetterBox by clearing all SingleBoxes
    public static void refresh() {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j].refresh(4, " ");
            }
        }
        Position.setRow(0);
        Position.setCol(0);
    }
}
