/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Noor Swadi 22167422
 * This class handles the graphical components
 */
public class MainFrame extends JFrame {
    public void init(){
        setTitle("Wordle");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }       
}
