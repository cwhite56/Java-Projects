import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Class that represents the Battleship GUI
 */
public class BattleshipGUI {
    
    private static String[] letterAxis = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static boolean selected = false;

    public BattleshipGUI() {
        initJFrame();
        
    }
    /** Method to initialize all JComponents
     * 
     */
    public void initJFrame() {

        JFrame frame = new JFrame("Battleship");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(guessPanel);
    
        guessPanel.add(createButtonGrid(), BorderLayout.CENTER);

        guessPanel.add(createLetterAxis(), BorderLayout.WEST);

        guessPanel.add(createNumberAxis(), BorderLayout.SOUTH);

        JPanel submitArea = new JPanel();
        submitArea.setLayout(new BoxLayout(submitArea, BoxLayout.Y_AXIS));
        submitArea.setBackground(Color.LIGHT_GRAY);
        guessPanel.add(submitArea, BorderLayout.EAST);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        submitArea.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());
        submitArea.add(cancelButton);

        JPanel shipPlacementPanel = new JPanel();
        shipPlacementPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(shipPlacementPanel);

        shipPlacementPanel.add(createButtonGrid(), BorderLayout.CENTER);

        shipPlacementPanel.add(createLetterAxis(), BorderLayout.WEST);

        shipPlacementPanel.add(createNumberAxis(), BorderLayout.SOUTH);

        JPanel placeArea = new JPanel();
        placeArea.setLayout(new BoxLayout(placeArea, BoxLayout.Y_AXIS));
        placeArea.setBackground(Color.LIGHT_GRAY);
        shipPlacementPanel.add(placeArea, BorderLayout.EAST);

        JButton placeButton = new JButton("Place");
        placeArea.add(placeButton);

        frame.setVisible(true);
        

        
    }
    class SubmitButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {   
            }

        }
    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }

    }
    /**
     * Helper method to create the letter axis panel
     * @return letter axis panel
     */
    public static JPanel createLetterAxis() {
        JPanel letterAxisPanel = new JPanel();
        letterAxisPanel.setLayout(new BoxLayout(letterAxisPanel, BoxLayout.Y_AXIS));
        letterAxisPanel.setBackground(Color.LIGHT_GRAY);

        CompoundBorder letterAxisBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(12, 10, 12, 10));

        for (int i = 0; i < letterAxis.length; i++) {
            JLabel label = new JLabel(letterAxis[i]);
            label.setBorder(letterAxisBorder);
            letterAxisPanel.add(label);
        }

        return letterAxisPanel;

    }
    /**
     * Helper method to create the number axis panel
     * @return number axis panel
     */
    public static JPanel createNumberAxis() {
        JPanel numberAxisPanel = new JPanel();
        numberAxisPanel.setLayout(new BoxLayout(numberAxisPanel, BoxLayout.X_AXIS));
        numberAxisPanel.setBackground(Color.LIGHT_GRAY);

        CompoundBorder numberAxisBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel empty = new JLabel();
        empty.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        numberAxisPanel.add(empty);

        for (int i = 1; i <= letterAxis.length; i++) {
            JLabel label = new JLabel(Integer.toString(i));
            label.setBorder(numberAxisBorder);
            numberAxisPanel.add(label);
        }
        return numberAxisPanel;
    }
    /**
     * Helper method to create button grid panel
     * @return button grid panel
     */
    public static JPanel createButtonGrid() {
        JPanel buttonGrid = new JPanel();
        buttonGrid.setLayout(new GridLayout(10, 10));

        for (int i = 0; i < 100; i++) {
            JButton button = new JButton();
            buttonGrid.add(button);
        }
        return buttonGrid;
    }
}
