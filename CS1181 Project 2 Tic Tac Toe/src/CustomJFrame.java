import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Custom JFRame class for custom game Tic Tac Toe
 */
public class CustomJFrame extends JFrame {
    private JPanel root = new JPanel();
    private static ArrayList<CardPanel> row1 = new ArrayList<CardPanel>();
    private static ArrayList<CardPanel> row2 = new ArrayList<CardPanel>();
    private static ArrayList<CardPanel> row3 = new ArrayList<CardPanel>();

    public CustomJFrame() {
        super();
        initComponents();
        initJFrame();
    }

    /**
     * Method to initialize Java Swing components into frame
     * 
     */
    private void initComponents() {

        this.root.setLayout(new GridLayout(3, 3));
        JOptionPane.showMessageDialog(this, "On your turn, click a box to claim it. Connect three in a row to win!");
        // Create 9 card panels with a card layout for each play square
        for (int i = 0; i < 3; i++) {
            CardPanel cardPanel = new CardPanel();
            this.root.add(cardPanel);
            row1.add(cardPanel);
        }

        for (int i = 0; i < 3; i++) {
            CardPanel cardPanel = new CardPanel();
            this.root.add(cardPanel);
            row2.add(cardPanel);
        }

        for (int i = 0; i < 3; i++) {
            CardPanel cardPanel = new CardPanel();
            this.root.add(cardPanel);
            row3.add(cardPanel);
        }

    }

    /**
     * Method to initialize basic JFrame functionality.
     * This code was borrowed from the class examples of CS1181
     */
    private void initJFrame() {
        this.setContentPane(root);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tic-Tac-Toe");
        this.setResizable(true);
    }

    /**
     * Method to check for winning arangements
     * 
     * @return an int representing the winning player
     */
    public static int checkWin() {
        // Player 1 wins horizontally
        if (row1.get(0).getTopCard() == "xCard" && row1.get(0).getTopCard() == row1.get(1).getTopCard() &&
                row1.get(0).getTopCard() == row1.get(2).getTopCard()) {
            return 1;
        } else if (row2.get(0).getTopCard() == "xCard" && row2.get(0).getTopCard() == row2.get(1).getTopCard() &&
                row2.get(0).getTopCard() == row2.get(2).getTopCard()) {
            return 1;
        } else if (row3.get(0).getTopCard() == "xCard" && row3.get(0).getTopCard() == row3.get(1).getTopCard() &&
                row3.get(0).getTopCard() == row3.get(2).getTopCard()) {
            return 1;
        }
        // Player 1 wins vertically
        else if (row1.get(0).getTopCard() == "xCard" && row1.get(0).getTopCard() == row2.get(0).getTopCard() &&
                row1.get(0).getTopCard() == row3.get(0).getTopCard()) {
            return 1;
        } else if (row1.get(1).getTopCard() == "xCard" && row1.get(1).getTopCard() == row2.get(1).getTopCard() &&
                row1.get(1).getTopCard() == row3.get(1).getTopCard()) {
            return 1;
        } else if (row1.get(2).getTopCard() == "xCard" && row1.get(2).getTopCard() == row2.get(2).getTopCard() &&
                row1.get(2).getTopCard() == row3.get(2).getTopCard()) {
            return 1;
        }
        // Play 1 wins diagonally
        else if (row1.get(0).getTopCard() == "xCard" && row1.get(0).getTopCard() == row2.get(1).getTopCard() &&
                row1.get(0).getTopCard() == row3.get(2).getTopCard()) {
            return 1;
        } else if (row1.get(2).getTopCard() == "xCard" && row1.get(2).getTopCard() == row2.get(1).getTopCard() &&
                row1.get(2).getTopCard() == row3.get(0).getTopCard()) {
            return 1;
        }

        // Player 2 wins horizontally
        if (row1.get(0).getTopCard() == "oCard" && row1.get(0).getTopCard() == row1.get(1).getTopCard() &&
                row1.get(0).getTopCard() == row1.get(2).getTopCard()) {
            return 2;
        } else if (row2.get(0).getTopCard() == "oCard" && row2.get(0).getTopCard() == row2.get(1).getTopCard() &&
                row2.get(0).getTopCard() == row2.get(2).getTopCard()) {
            return 2;
        } else if (row3.get(0).getTopCard() == "oCard" && row3.get(0).getTopCard() == row3.get(1).getTopCard() &&
                row3.get(0).getTopCard() == row3.get(2).getTopCard()) {
            return 2;
        }
        // Player 2 wins vertically
        else if (row1.get(0).getTopCard() == "oCard" && row1.get(0).getTopCard() == row2.get(0).getTopCard() &&
                row1.get(0).getTopCard() == row3.get(0).getTopCard()) {
            return 2;
        } else if (row1.get(1).getTopCard() == "oCard" && row1.get(1).getTopCard() == row2.get(1).getTopCard() &&
                row1.get(1).getTopCard() == row3.get(1).getTopCard()) {
            return 2;
        } else if (row1.get(2).getTopCard() == "oCard" && row1.get(2).getTopCard() == row2.get(2).getTopCard() &&
                row1.get(2).getTopCard() == row3.get(2).getTopCard()) {
            return 2;
        }
        // Play 2 wins diagonally
        else if (row1.get(0).getTopCard() == "oCard" && row1.get(0).getTopCard() == row2.get(1).getTopCard() &&
                row1.get(0).getTopCard() == row3.get(2).getTopCard()) {
            return 2;
        } else if (row1.get(2).getTopCard() == "oCard" && row1.get(2).getTopCard() == row2.get(1).getTopCard() &&
                row1.get(2).getTopCard() == row3.get(0).getTopCard()) {
            return 2;
        } else {
            return 0;
        }
    }

}
