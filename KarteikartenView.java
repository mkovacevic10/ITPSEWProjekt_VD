import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KarteikartenView extends JFrame {
    private JLabel cardLabel, solutionLabel;
    private JButton knowButton, nextButton, dontKnowButton, addButton, exitButton, solutionButton;

    public KarteikartenView() {
        setTitle("Karteikarten");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setLocationRelativeTo(null);

        cardLabel = new JLabel("", SwingConstants.CENTER);
        solutionLabel = new JLabel("", SwingConstants.CENTER);
        add(cardLabel);
        add(solutionLabel);

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
        solutionLabel.setText("");
    }

    public void showSolution(String solution) {
        solutionLabel.setText("Lösung: " + solution);
    }

    public void hideSolution() {
        solutionLabel.setText("");
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

    public void addAddCardListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addSolutionListener(ActionListener listener) {
        solutionButton.addActionListener(listener);
    }
}
