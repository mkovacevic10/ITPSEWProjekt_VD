import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeichernLaden extends JFrame {
    private JButton speichernButton, ladenButton, zurueckButton;

    public SpeichernLaden(HauptmenueController ctrl) {
        setTitle("Speichern und Laden");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        speichernButton = new JButton("Speichern");
        ladenButton = new JButton("Laden");
        zurueckButton = new JButton("Zurück zum Hauptmenü");

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String verzeichnis = JOptionPane.showInputDialog("Verzeichnis zum Speichern eingeben:");
            }
        });

        ladenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String verzeichnis = JOptionPane.showInputDialog("Verzeichnis zum Laden eingeben:");
                ctrl.setVerzeichnis(verzeichnis);
            }
        });

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ctrl.getView().setVisible(true);
            }
        });

        add(ladenButton);
        add(zurueckButton);
    }

    public void visibilty(boolean g){
        setVisible(g);
    }
}
