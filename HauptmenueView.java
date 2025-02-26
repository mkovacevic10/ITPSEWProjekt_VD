import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HauptmenueView {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton lernkarteiButton, quizButton, spielButton, dateiButton, schließenButton;

    public HauptmenueView() {
        // Initialisiere das Hauptfenster
        frame = new JFrame("ITP Lernbegriffe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Hauptpanel mit GridLayout (7 Zeilen und 1 Spalte für Buttons)
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1)); // 7 Zeilen, 1 Spalte für das Hauptlayout

        // Titel hinzufügen und zentrieren
        JLabel titleLabel = new JLabel("ITP Lernbegriffe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel); // Hinzufügen des Titel-Labels

        // Füge leere Zelle hinzu, um Abstand nach oben zu erzeugen
        mainPanel.add(new JLabel(""));  // Leere Zelle als Abstand nach oben

        // Panel für die drei Buttons oben (3 Buttons nebeneinander)
        JPanel buttonPanelTop = new JPanel();
        buttonPanelTop.setLayout(new GridLayout(1, 3, 10, 0)); // 1 Zeile, 3 Spalten, horizontaler Abstand = 10

        lernkarteiButton = new JButton("Lernkartei");
        quizButton = new JButton("Quiz");
        spielButton = new JButton("Spiel");

        // Alle Buttons auf gleiche Größe setzen
        setButtonSize(lernkarteiButton);
        setButtonSize(quizButton);
        setButtonSize(spielButton);

        buttonPanelTop.add(lernkarteiButton);
        buttonPanelTop.add(quizButton);
        buttonPanelTop.add(spielButton);

        mainPanel.add(buttonPanelTop);  // Hinzufügen des Panels für obere Buttons

        // Füge leere Zelle hinzu, um Abstand zwischen den Panels zu erzeugen
        mainPanel.add(new JLabel(""));  // Leere Zelle als Abstand

        // Panel für die unteren Buttons (2 Buttons untereinander)
        JPanel buttonPanelBottom = new JPanel();
        buttonPanelBottom.setLayout(new GridLayout(2, 1, 10, 10)); // 2 Zeilen, 1 Spalte, vertikaler Abstand = 10

        dateiButton = new JButton("Datei speichern/bearbeiten");
        schließenButton = new JButton("Programm schließen");

        // Alle unteren Buttons auf gleiche Größe setzen
        setButtonSize(dateiButton);
        setButtonSize(schließenButton);

        buttonPanelBottom.add(dateiButton);
        buttonPanelBottom.add(schließenButton);

        mainPanel.add(buttonPanelBottom);  // Hinzufügen des Panels für untere Buttons

        // Füge leere Zelle hinzu, um Abstand unten zu erzeugen
        mainPanel.add(new JLabel(""));  // Leere Zelle als Abstand nach unten

        // Alle Komponenten dem Hauptpanel hinzufügen
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Fenster zentrieren
    }

    // Methode, um allen Buttons die gleiche Größe zu geben
    private void setButtonSize(JButton button) {
        button.setPreferredSize(new Dimension(150, 40));  // Beispielgröße für alle Buttons
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void addLernkarteiListener(ActionListener listen) {
        lernkarteiButton.addActionListener(listen);
    }

    public void addQuizListener(ActionListener listen) {
        quizButton.addActionListener(listen);
    }

    public void addSpielListener(ActionListener listen) {
        spielButton.addActionListener(listen);
    }

    public void addDateiListener(ActionListener listen) {
        dateiButton.addActionListener(listen);
    }

    public void addSchließenListener(ActionListener listen) {
        schließenButton.addActionListener(listen);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void closeProgram() {
        int result = JOptionPane.showConfirmDialog(frame, "Möchtest du das Programm wirklich schließen?", "Bestätigung", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }

    public void nextProgram() {
        frame.dispose();
    }
}