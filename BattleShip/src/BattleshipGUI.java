import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class BattleshipGUI {
    
    private String[] letterAxis = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    public BattleshipGUI() {
        initJFrame();
        
    }
    public void initJFrame() {
        JFrame frame = new JFrame("Battleship");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(10, 10));
        frame.getContentPane().add(gridPanel, BorderLayout.CENTER);

        for (int i = 0; i < 100; i++) {
            JButton button = new JButton();
            gridPanel.add(button);
        }
            
        

        JPanel letterAxisPanel = new JPanel();
        letterAxisPanel.setLayout(new BoxLayout(letterAxisPanel, BoxLayout.Y_AXIS));
        letterAxisPanel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(letterAxisPanel, BorderLayout.WEST);

        for (int i = 0; i < letterAxis.length; i++) {
            JLabel label = new JLabel(letterAxis[i]);
            label.setBorder(BorderFactory.createEmptyBorder(16, 10, 10, 10));
            letterAxisPanel.add(label);
        }

        JPanel numberAxisPanel = new JPanel();
        numberAxisPanel.setLayout(new BoxLayout(numberAxisPanel, BoxLayout.X_AXIS));
        numberAxisPanel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(numberAxisPanel, BorderLayout.SOUTH);
        JLabel empty = new JLabel();
        empty.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        numberAxisPanel.add(empty);
        for (int i = 1; i <= letterAxis.length; i++) {
            JLabel label = new JLabel(Integer.toString(i));
            label.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 20));
            numberAxisPanel.add(label);
        }
        frame.setVisible(true);
        

        
    }
}
