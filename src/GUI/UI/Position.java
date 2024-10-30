/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

/**
 *
 * @author Noor Swadi 22167422
 * Store current input position and provides getters and setters.
 */

public class Position {

    private static int row = 0; // Current row

    private static int col = 0; // Current coloumn

    public static int getRow(){
        return row;
    }

    public static int getCol(){
        return col;
    }

    public static void setRow(int r){
        Position.row = r;
    }

    public static void setCol(int c){
        Position.col = c;
    }
}
