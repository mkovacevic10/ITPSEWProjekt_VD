import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeichernLaden extends JFrame {
    private JButton speichernButton, ladenButton, zurueckButton; // Buttons für die Aktionen

    public SpeichernLaden(HauptmenueController ctrl) {
        setTitle("Speichern und Laden"); // Titel des Fensters setzen
        setSize(400, 300); // Größe des Fensters setzen
        setLocationRelativeTo(null); // Fenster zentrieren
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beenden der Anwendung beim Schließen
        setLayout(new GridLayout(3, 1)); // Layout mit 3 Zeilen und 1 Spalte setzen

        // Buttons initialisieren
        speichernButton = new JButton("Speichern");
        ladenButton = new JButton("Laden");
        zurueckButton = new JButton("Zurück zum Hauptmenü");

        // ActionListener für den "Speichern"-Button
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eingabeaufforderung für den Benutzer, um ein Speicherverzeichnis einzugeben
                String verzeichnis = JOptionPane.showInputDialog("Verzeichnis zum Speichern eingeben:");
                // Hier könnte man die Speicherlogik implementieren
            }
        });

        // ActionListener für den "Laden"-Button
        ladenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eingabeaufforderung für den Benutzer, um ein Verzeichnis zum Laden anzugeben
                String verzeichnis = JOptionPane.showInputDialog("Verzeichnis zum Laden eingeben:");
                ctrl.setVerzeichnis(verzeichnis); // Setzt das Verzeichnis im Hauptmenü-Controller
            }
        });

        // ActionListener für den "Zurück zum Hauptmenü"-Button
        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Aktuelles Fenster ausblenden
                ctrl.getView().setVisible(true); // Hauptmenü wieder sichtbar machen
            }
        });

        // Buttons zum Fenster hinzufügen
        add(ladenButton);
        add(zurueckButton);
    }

    // Methode zur Steuerung der Sichtbarkeit des Fensters
    public void visibilty(boolean g){
        setVisible(g);
    }
}