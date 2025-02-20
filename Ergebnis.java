import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ergebnis extends JFrame {
    public Ergebnis(int correct, int wrong, HauptmenueController contr) {
        setTitle("Ergebnis");
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));
        setLocationRelativeTo(null);

        JLabel scoreLabel = new JLabel("Weiß ich: " + correct + " | Weiß ich nicht: " + wrong, SwingConstants.CENTER);
        add(scoreLabel);

        JButton backButton = new JButton("Zurück zum Hauptmenü");
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                contr.getView().setVisible(true);
            }
        });
    }
}
