/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Modul5;

/**
 *
 * @author Muhammad Yafi
 */

    import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela Pertamaku");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                JLabel label = new JLabel("label ada diatas (NORTH)");
                JButton buttonSouth = new JButton("tombol dibawah (SOUTH)");
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");

                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH di KLIK!");
                });

                buttonWest.addActionListener(e -> {
                    label.setText("Tombol di WEST di KLIK!");
                });

                buttonEast.addActionListener(e -> {
                    label.setText("Tombol di EAST di KLIK!");
                });

                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol di CENTER di KLIK!");
                });

                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
