import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spiel extends JFrame {
    private JLabel wordLabel;
    private JButton nextWordButton, backButton;
    private JLabel triesLabel, correctLettersLabel, questionLabel;
    private JPanel keyboardPanel;
    private int tries = 10;
    private String answer;
    private String question;
    private char[] guessedAnswer;
    private JButton[] letterButtons;
    private HauptmenueController contr;

    public Spiel(String question, String answer, HauptmenueController contr) {
        this.answer = answer.trim().toUpperCase();
        this.question = question;
        this.guessedAnswer = new char[answer.length()];

        for (int i = 0; i < guessedAnswer.length; i++) {
            guessedAnswer[i] = (answer.charAt(i) == ' ') ? ' ' : '_'; // Leerzeichen berücksichtigen
        }

        this.contr = contr;

        setTitle("ITP-Hangman");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        JPanel questionPanel = new JPanel(new GridLayout(1, 1));
        questionLabel = new JLabel(question, SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionPanel.add(questionLabel);
        add(questionPanel);

        JPanel topPanel = new JPanel(new GridLayout(1, 1));
        wordLabel = new JLabel(new String(guessedAnswer), SwingConstants.CENTER);
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
        letterButtons = new JButton[26];
        for (char c = 'A'; c <= 'Z'; c++) {
            final char letter = c;
            JButton letterButton = new JButton(String.valueOf(c));
            letterButton.setPreferredSize(new Dimension(50, 50));
            letterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkLetter(letter, letterButton);
                }
            });
            letterButtons[c - 'A'] = letterButton;
            keyboardPanel.add(letterButton);
        }
        add(keyboardPanel);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        backButton = new JButton("Zurück zum Hauptmenü");
        nextWordButton = new JButton("Nächster Begriff");
        bottomPanel.add(backButton);
        bottomPanel.add(nextWordButton);
        add(bottomPanel);

        backButton.addActionListener(e -> {
            dispose();
            contr.getView().setVisible(true);
        });

        nextWordButton.addActionListener(e -> {
            String[] nextQuestionAndAnswer = contr.getRandomQuestionAndAnswer("C:\\itp-programm\\questions.txt", "C:\\itp-programm\\answers.txt");
            updateGame(nextQuestionAndAnswer[0], nextQuestionAndAnswer[1]);
        });

        setVisible(true);
    }

    private void checkLetter(char letter, JButton button) {
        boolean correct = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter) {
                guessedAnswer[i] = letter;
                correct = true;
            }
        }

        button.setEnabled(false);

        if (!correct) {
            tries--;
        }

        wordLabel.setText(new String(guessedAnswer));
        triesLabel.setText("Anzahl Versuche: " + tries);

        if (isAnswerGuessed()) {
            showWinningMessage();
        } else if (tries <= 0) {
            showLosingMessage();
        }
    }

    private void updateGame(String newQuestion, String newAnswer) {
        this.question = newQuestion;
        this.answer = newAnswer.trim().toUpperCase();
        this.guessedAnswer = new char[answer.length()];

        for (int i = 0; i < guessedAnswer.length; i++) {
            guessedAnswer[i] = (answer.charAt(i) == ' ') ? ' ' : '_';
        }

        wordLabel.setText(new String(guessedAnswer));
        questionLabel.setText(question);
        tries = 10;
        triesLabel.setText("Anzahl Versuche: " + tries);

        for (JButton button : letterButtons) {
            button.setEnabled(true);
        }
    }

    private boolean isAnswerGuessed() {
        for (char c : guessedAnswer) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private void showWinningMessage() {
        JOptionPane.showMessageDialog(this, "Glückwunsch! Du hast die Antwort erraten.");
        disableAllButtons();
    }

    private void showLosingMessage() {
        JOptionPane.showMessageDialog(this, "Verloren... Du hast keine Versuche mehr. Die Antwort war: " + answer);
        disableAllButtons();
    }

    private void disableAllButtons() {
        for (JButton button : letterButtons) {
            button.setEnabled(false);
        }
    }
}
