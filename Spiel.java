import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spiel extends JFrame {
    private JLabel wordLabel; // Zeigt das aktuell geratene Wort
    private JButton nextWordButton, backButton; // Buttons für "Nächster Begriff" und "Zurück zum Hauptmenü"
    private JLabel triesLabel, correctLettersLabel, questionLabel; // Labels für Versuche, richtige Buchstaben und Frage
    private JPanel keyboardPanel; // Panel für die Buchstaben-Tastatur
    private int tries = 10; // Maximale Anzahl an Versuchen
    private String answer; // Die Antwort, die der Benutzer erraten muss
    private String question; // Die Frage, die dem Benutzer angezeigt wird
    private char[] guessedAnswer; // Array für das geratene Wort (mit '_' für nicht erratene Buchstaben)
    private JButton[] letterButtons; // Buttons für die Buchstaben-Tastatur
    private HauptmenueController contr; // Controller, um mit dem Hauptmenü zu kommunizieren

    public Spiel(String question, String answer, HauptmenueController contr) {
        this.answer = answer.trim().toUpperCase(); // Antwort auf Großbuchstaben trimmen
        this.question = question;
        this.guessedAnswer = new char[answer.length()];

        // Erstelle ein Array mit '_' für jedes Zeichen der Antwort (bzw. ' ' für Leerzeichen)
        for (int i = 0; i < guessedAnswer.length; i++) {
            guessedAnswer[i] = (answer.charAt(i) == ' ') ? ' ' : '_';
        }

        this.contr = contr; // Setze den Controller

        setTitle("ITP-Hangman"); // Fenster-Titel setzen
        setSize(800, 500); // Fenstergröße
        setLocationRelativeTo(null); // Fenster zentrieren
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beim Schließen des Fensters die Anwendung beenden
        setLayout(new GridLayout(6, 1)); // Layout für das Fenster setzen

        // Frage-Panel erstellen
        JPanel questionPanel = new JPanel(new GridLayout(1, 1));
        questionLabel = new JLabel(question, SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionPanel.add(questionLabel);
        add(questionPanel);

        // Top-Panel für das geratene Wort
        JPanel topPanel = new JPanel(new GridLayout(1, 1));
        wordLabel = new JLabel(new String(guessedAnswer), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(wordLabel);
        add(topPanel);

        // Info-Panel für Versuche und richtige Buchstaben
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        triesLabel = new JLabel("Anzahl Versuche: " + tries);
        correctLettersLabel = new JLabel("Richtige Buchstaben: ");
        infoPanel.add(triesLabel);
        infoPanel.add(correctLettersLabel);
        add(infoPanel);

        // Panel für die Buchstaben-Tastatur
        keyboardPanel = new JPanel(new GridLayout(3, 9)); // 3 Zeilen, 9 Spalten
        letterButtons = new JButton[26]; // Array für die Buchstaben-Tasten

        // Schleife für alle Buchstaben A-Z
        for (char c = 'A'; c <= 'Z'; c++) {
            final char letter = c;
            JButton letterButton = new JButton(String.valueOf(c));
            letterButton.setPreferredSize(new Dimension(50, 50)); // Größe der Buttons setzen
            letterButton.addActionListener(new ActionListener() { // ActionListener für den Button
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkLetter(letter, letterButton); // Buchstaben prüfen
                }
            });
            letterButtons[c - 'A'] = letterButton; // Button im Array speichern
            keyboardPanel.add(letterButton); // Button zum Panel hinzufügen
        }
        add(keyboardPanel);

        // Bottom-Panel mit den Buttons für Zurück und Nächster Begriff
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        backButton = new JButton("Zurück zum Hauptmenü");
        nextWordButton = new JButton("Nächster Begriff");
        bottomPanel.add(backButton);
        bottomPanel.add(nextWordButton);
        add(bottomPanel);

        // ActionListener für den "Zurück zum Hauptmenü"-Button
        backButton.addActionListener(e -> {
            dispose(); // Schließe das aktuelle Fenster
            contr.getView().setVisible(true); // Zeige das Hauptmenü
        });

        // ActionListener für den "Nächster Begriff"-Button
        nextWordButton.addActionListener(e -> {
            // Zufällige Frage und Antwort aus dem Controller holen
            String[] nextQuestionAndAnswer = contr.getRandomQuestionAndAnswer(contr.getVerzeichnis()+"\\questions.txt", contr.getVerzeichnis()+"\\answers.txt");
            updateGame(nextQuestionAndAnswer[0], nextQuestionAndAnswer[1]); // Spiel mit neuen Werten aktualisieren
        });

        setVisible(true); // Setze das Fenster sichtbar
    }

    // Methode, um einen Buchstaben zu überprüfen
    private void checkLetter(char letter, JButton button) {
        boolean correct = false;
        // Schleife, um die Antwort zu durchsuchen und zu prüfen, ob der Buchstabe korrekt ist
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter) {
                guessedAnswer[i] = letter; // Wenn der Buchstabe korrekt ist, ersetze '_' durch den Buchstaben
                correct = true;
            }
        }

        button.setEnabled(false); // Deaktiviere den Button, nachdem er gedrückt wurde

        if (!correct) {
            tries--; // Wenn der Buchstabe falsch war, verringere die Versuche
        }

        wordLabel.setText(new String(guessedAnswer)); // Aktualisiere die Anzeige des gerateten Wortes
        triesLabel.setText("Anzahl Versuche: " + tries); // Aktualisiere die verbleibenden Versuche

        if (isAnswerGuessed()) {
            showWinningMessage(); // Wenn das Wort erraten wurde, zeige eine Gewinn-Nachricht
        } else if (tries <= 0) {
            showLosingMessage(); // Wenn keine Versuche mehr übrig sind, zeige eine Verlust-Nachricht
        }
    }

    // Methode, um das Spiel mit neuen Frage- und Antwortwerten zu aktualisieren
    private void updateGame(String newQuestion, String newAnswer) {
        this.question = newQuestion;
        this.answer = newAnswer.trim().toUpperCase();
        this.guessedAnswer = new char[answer.length()];

        // Initialisiere das guessedAnswer-Array mit '_' für jedes Zeichen der Antwort
        for (int i = 0; i < guessedAnswer.length; i++) {
            guessedAnswer[i] = (answer.charAt(i) == ' ') ? ' ' : '_';
        }

        wordLabel.setText(new String(guessedAnswer)); // Setze das neue Wort zum Raten
        questionLabel.setText(question); // Setze die neue Frage
        tries = 10; // Setze die Versuche zurück
        triesLabel.setText("Anzahl Versuche: " + tries);

        // Reaktiviere alle Buchstaben-Buttons
        for (JButton button : letterButtons) {
            button.setEnabled(true);
        }
    }

    // Methode, um zu prüfen, ob das Wort vollständig erraten wurde
    private boolean isAnswerGuessed() {
        for (char c : guessedAnswer) {
            if (c == '_') {
                return false; // Falls ein '_' gefunden wird, ist das Wort noch nicht vollständig erraten
            }
        }
        return true;
    }

    // Methode, um eine Gewinn-Nachricht anzuzeigen
    private void showWinningMessage() {
        JOptionPane.showMessageDialog(this, "Glückwunsch! Du hast die Antwort erraten.");
        disableAllButtons(); // Deaktiviere alle Buchstaben-Buttons
    }

    // Methode, um eine Verlust-Nachricht anzuzeigen
    private void showLosingMessage() {
        JOptionPane.showMessageDialog(this, "Verloren... Du hast keine Versuche mehr. Die Antwort war: " + answer);
        disableAllButtons(); // Deaktiviere alle Buchstaben-Buttons
    }

    // Methode, um alle Buttons zu deaktivieren
    private void disableAllButtons() {
        for (JButton button : letterButtons) {
            button.setEnabled(false);
        }
    }
}