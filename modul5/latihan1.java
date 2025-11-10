  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Modul5;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Muhammad Yafi
 */
public class latihan1 {
       public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        public void run(){
            JFrame frame = new JFrame("Jendela Pertamaku");
            frame.setSize(400,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
                     
        }
        });
       }
}