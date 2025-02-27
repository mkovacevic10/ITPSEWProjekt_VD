import javax.swing.*;  // Importiert die Swing-Bibliothek für die GUI-Komponenten
import java.awt.*;     // Importiert die AWT-Bibliothek für Layouts und andere grafische Funktionen

// Die Klasse QuizView erbt von JFrame und stellt das GUI-Fenster für das Quiz dar
public class QuizView extends JFrame {
    // GUI-Komponenten als private Attribute
    private JLabel questionLabel;      // Anzeige für die Frage
    private JTextField answerField;    // Eingabefeld für die Antwort
    private JButton submitButton;      // Button zum Bestätigen der Antwort
    private JButton retryButton;       // Button zum Wiederholen des Quiz
    private JButton mainMenuButton;    // Button zur Rückkehr ins Hauptmenü
    private JPanel mainPanel;          // Panel zur Organisation der GUI-Elemente

    // Konstruktor der Klasse QuizView
    public QuizView() {
        setTitle("Quiz");                 // Setzt den Fenstertitel
        setSize(700, 600);                 // Definiert die Fenstergröße
        setLayout(new GridBagLayout());    // Verwendet GridBagLayout für flexible Platzierung
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Schließt das Programm beim Schließen des Fensters

        // Erstellt das Hauptpanel mit einem GridLayout (6 Reihen, 1 Spalte, Abstand 10px vertikal)
        mainPanel = new JPanel(new GridLayout(6, 1, 0, 10));

        // Initialisiert das Frage-Label
        questionLabel = new JLabel("", SwingConstants.CENTER); // Zentriert den Text
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Setzt die Schriftart und -größe

        // Initialisiert das Eingabefeld
        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 24)); // Setzt die Schriftart und -größe

        // Initialisiert die Buttons mit entsprechenden Beschriftungen
        submitButton = new JButton("Eingabe bestätigen");
        retryButton = new JButton("Quiz wiederholen");
        mainMenuButton = new JButton("Hauptmenü");

        // Versteckt die Buttons "Quiz wiederholen" und "Hauptmenü" zunächst
        retryButton.setVisible(false);
        mainMenuButton.setVisible(false);

        // Fügt die Komponenten dem Hauptpanel hinzu
        mainPanel.add(questionLabel);
        mainPanel.add(answerField);
        mainPanel.add(submitButton);
        mainPanel.add(retryButton);
        mainPanel.add(mainMenuButton);

        // Legt die Position des Panels im Layout fest
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // X-Position in der GridBagLayout
        gbc.gridy = 0; // Y-Position in der GridBagLayout
        gbc.fill = GridBagConstraints.HORIZONTAL; // Füllt horizontal den verfügbaren Platz
        add(mainPanel, gbc); // Fügt das Hauptpanel zum JFrame hinzu

        setVisible(true); // Zeigt das Fenster an
    }

    // Entfernt alle Komponenten aus dem Panel (zum Zurücksetzen des Layouts)
    public void clearLayout() {
        mainPanel.removeAll();
    }

    // Setzt eine neue Frage im Label
    public void setQuestion(String question) {
        questionLabel.setText(question);
    }

    // Löscht den Inhalt des Antwortfeldes
    public void clearAnswerField() {
        answerField.setText("");
    }

    // Gibt den aktuellen Inhalt des Antwortfeldes zurück
    public String getAnswer() {
        return answerField.getText();
    }

    // Getter-Methode für den "Eingabe bestätigen"-Button
    public JButton getSubmitButton() {
        return submitButton;
    }

    // Getter-Methode für den "Quiz wiederholen"-Button
    public JButton getRetryButton() {
        return retryButton;
    }

    // Getter-Methode für den "Hauptmenü"-Button
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
