import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KarteikartenView extends JFrame {
    private JLabel cardLabel;
    private JButton knowButton, nextButton, dontKnowButton, addButton, exitButton, solutionButton;

    public KarteikartenView() {
        setTitle("Karteikarten");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);

        cardLabel = new JLabel("", SwingConstants.CENTER);
        add(cardLabel);

        JPanel mainButtonPanel = new JPanel(new GridLayout(2, 3));

        knowButton = new JButton("Weiß ich");
        nextButton = new JButton("Nächster Begriff");
        dontKnowButton = new JButton("Weiß ich nicht");
        solutionButton = new JButton("Lösung hinzufügen");
        exitButton = new JButton("Beenden");
        addButton = new JButton("Karteikarten hinzufügen");

        mainButtonPanel.add(knowButton);
        mainButtonPanel.add(nextButton);
        mainButtonPanel.add(dontKnowButton);
        mainButtonPanel.add(solutionButton);
        mainButtonPanel.add(exitButton);
        mainButtonPanel.add(addButton);

        add(mainButtonPanel);
    }

    public void setCardText(String text) {
        cardLabel.setText(text);
    }

    public void addNextListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void addCorrectListener(ActionListener listener) {
        knowButton.addActionListener(listener);
    }

    public void addWrongListener(ActionListener listener) {
        dontKnowButton.addActionListener(listener);
    }
}