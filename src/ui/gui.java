package ui;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gui extends JFrame implements ActionListener{
    private JLabel label;
    private JTextField tf;
    private JButton button;

    public gui () {
        super("Soteria");
        setLayout(new FlowLayout());

        tf = new JTextField (20);
        add(tf);

        button = new JButton ("Enter");
        button.addActionListener(this);
        add(button);

        label = new JLabel("Enter the load directory");
        add(label);

        block_entry();
    }

    public static void createAndShowGUI () {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window
        JFrame frame = new gui();
        Image icon = Toolkit.getDefaultToolkit().getImage("../data/gradient_icon.png");
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size and location
        frame.setSize(3000, 3000);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application a GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public void actionPerformed (ActionEvent e) {
        String load_path;
        if (e.getSource() == button) {
            load_path = tf.getText();
            System.out.println(load_path);
        }
    }

    public void block_entry () {
        JTextField tf = new JTextField (20);
        add(tf);
    }
}
