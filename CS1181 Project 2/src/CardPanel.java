import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Custon JPanel class that represents a single play square on the board
 */
public class CardPanel extends JPanel implements ActionListener {

    private CardLayout cardLayout = new CardLayout();
    private static int turnCounter = 0;

    public CardPanel() {

        super();
        this.setLayout(cardLayout);

        // Create all three cards necessary
        JPanel emptyCard = new JPanel();
        this.add(emptyCard, "emptyCard");
        emptyCard.setName("emptyCard");

        JButton nextButton = new JButton("Play");
        nextButton.addActionListener(this);
        emptyCard.add(nextButton);

        JPanel xCard = new JPanel();
        this.add(xCard, "xCard");
        xCard.add(new JLabel("X"));
        xCard.setName("xCard");

        JPanel oCard = new JPanel();
        this.add(oCard, "oCard");
        oCard.add(new JLabel("O"));
        oCard.setName("oCard");

    }

    /**
     * Overriden action performed to alternate turns and their coresponding shapes
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        turnCounter++;

        if (turnCounter % 2 != 0) {
            this.cardLayout.next(this);
        } else {
            this.cardLayout.last(this);
        }

        // Check for a winner
        int winner = CustomJFrame.checkWin();
        if (winner == 1 || winner == 2) {
            // Borrowed this use of JOptionPane from our Zybooks assignment
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        } else if (turnCounter == 9) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
        }
    }

    /**
     * Method to return the string value of the currently visable card
     * 
     * @return
     */
    public String getTopCard() {
        // Code learned from Stack Overflow on how to return the currently displayed
        // card
        JPanel card = null;

        for (Component comp : this.getComponents()) {
            if (comp.isVisible() == true) {
                card = (JPanel) comp;
            }
        }
        return card.getName();
    }

}
