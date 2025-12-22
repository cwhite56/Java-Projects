import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BattleshipGUI {
    
    private String[] letterAxis = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static boolean selected = false;
    private ArrayList<JButton> buttonList = new ArrayList<>();
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
            buttonList.add(button);
            gridPanel.add(button);
            button.addActionListener((e) -> {if (!selected) {
                selected = true;
                button.setBackground(Color.BLUE);
            }} );
        }
            
        

        JPanel letterAxisPanel = new JPanel();
        letterAxisPanel.setLayout(new BoxLayout(letterAxisPanel, BoxLayout.Y_AXIS));
        letterAxisPanel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(letterAxisPanel, BorderLayout.WEST);

        CompoundBorder letterAxisBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(14, 10, 10, 10));

        for (int i = 0; i < letterAxis.length; i++) {
            JLabel label = new JLabel(letterAxis[i]);
            label.setBorder(letterAxisBorder);
            letterAxisPanel.add(label);
        }

        JPanel numberAxisPanel = new JPanel();
        numberAxisPanel.setLayout(new BoxLayout(numberAxisPanel, BoxLayout.X_AXIS));
        numberAxisPanel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(numberAxisPanel, BorderLayout.SOUTH);

        CompoundBorder numberAxisBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(10, 13, 10, 15));

        JLabel empty = new JLabel();
        empty.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 22));
        numberAxisPanel.add(empty);

        for (int i = 1; i <= letterAxis.length; i++) {
            JLabel label = new JLabel(Integer.toString(i));
            label.setBorder(numberAxisBorder);
            numberAxisPanel.add(label);
        }

        JPanel submitArea = new JPanel();
        submitArea.setLayout(new BoxLayout(submitArea, BoxLayout.Y_AXIS));
        submitArea.setBackground(Color.LIGHT_GRAY);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());
        frame.getContentPane().add(submitArea, BorderLayout.EAST);
        submitArea.add(submitButton);
        submitArea.add(cancelButton);
        frame.setVisible(true);
        

        
    }
    class SubmitButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected) {
                    for (int i = 0; i < buttonList.size(); i++) {
                        buttonList.get(i).setBackground(new JButton().getBackground());
                    }
                    selected = false;
                }
            }

        }
    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selected) {
                    for (int i = 0; i < buttonList.size(); i++) {
                        buttonList.get(i).setBackground(new JButton().getBackground());
                    }
                    selected = false;
                }
        }

    }
}
