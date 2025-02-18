package Hauptmenue;
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

        // Hauptpanel mit BoxLayout auf der Y-Achse
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titel hinzufügen und zentrieren
        JLabel titleLabel = new JLabel("ITP Lernbegriffe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(20)); // Abstand oben
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30)); // Abstand nach dem Titel

        // Panel für die drei Buttons nebeneinander zentriert
        JPanel buttonPanelTop = new JPanel();
        buttonPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        lernkarteiButton = new JButton("Lernkartei");
        quizButton = new JButton("Quiz");
        spielButton = new JButton("Spiel");

        buttonPanelTop.add(lernkarteiButton);
        buttonPanelTop.add(quizButton);
        buttonPanelTop.add(spielButton);

        // Panel für die unteren Buttons untereinander zentriert
        JPanel buttonPanelBottom = new JPanel();
        buttonPanelBottom.setLayout(new BoxLayout(buttonPanelBottom, BoxLayout.Y_AXIS));

        dateiButton = new JButton("Datei speichern/bearbeiten");
        schließenButton = new JButton("Programm schließen");

        dateiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        schließenButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanelBottom.add(Box.createVerticalStrut(20)); // Abstand oben
        buttonPanelBottom.add(dateiButton);
        buttonPanelBottom.add(Box.createVerticalStrut(10)); // Abstand zwischen den Buttons
        buttonPanelBottom.add(schließenButton);

        // Alle Komponenten dem Hauptpanel hinzufügen
        mainPanel.add(buttonPanelTop);
        mainPanel.add(buttonPanelBottom);

        // Hauptpanel dem Frame hinzufügen
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Fenster zentrieren
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
}
