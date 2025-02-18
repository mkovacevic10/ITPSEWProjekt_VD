import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spiel extends JFrame {
    private JLabel wordLabel;
    private JButton nextWordButton, backButton;
    private JLabel triesLabel, correctLettersLabel;
    private JPanel keyboardPanel;
    private int tries = 10;
    private String word;
    private char[] guessedWord;

    public Spiel(String wort) {
        this.word = wort.toUpperCase();
        this.guessedWord = new char[word.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
        setTitle("ITP-Hangman");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JPanel topPanel = new JPanel(new GridLayout(1, 1));
        wordLabel = new JLabel(new String(guessedWord), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(wordLabel);
        add(topPanel);

        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        triesLabel = new JLabel("Anzahl Versuche: " + tries);
        correctLettersLabel = new JLabel("Richtige Buchstaben: ");
        infoPanel.add(triesLabel);
        infoPanel.add(correctLettersLabel);
        add(infoPanel);

        keyboardPanel = new JPanel(new GridLayout(3, 9));
        String vergleich="";
        for (char c = 'A'; c <= 'Z'; c++) {
            final char letter = c;
            JButton letterButton = new JButton(String.valueOf(c));
            letterButton.setPreferredSize(new Dimension(50, 50));
            letterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean correct = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == letter) {
                            guessedWord[i] = letter;
                            correct = true;
                        }
                    }

                    if (!correct) {
                        tries--;
                    }
                    letterButton.setEnabled(false);
                    wordLabel.setText(new String(guessedWord));
                    triesLabel.setText("Anzahl Versuche: " + tries);
                }
            });
            keyboardPanel.add(letterButton);
        }
        add(keyboardPanel);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        backButton = new JButton("Zurück zum Hauptmenü");
        nextWordButton = new JButton("Nächster Begriff");
        bottomPanel.add(backButton);
        bottomPanel.add(nextWordButton);
        add(bottomPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Spiel("TEST");
    }
}