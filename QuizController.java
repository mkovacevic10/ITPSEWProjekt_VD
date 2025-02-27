import javax.swing.*;               // Import für GUI-Elemente
import java.awt.event.ActionEvent;  // Import für ActionEvent (Button-Klicks)
import java.awt.event.ActionListener; // Import für Event-Handling

// Die Klasse QuizController verwaltet die Steuerung der Quiz-Anwendung
public class QuizController {
    private QuizModel model;       // Das Quiz-Modell (Logik und Daten)
    private QuizView view;         // Die Benutzeroberfläche (GUI)
    private HauptmenueController contr; // Referenz zum Hauptmenü-Controller

    // Konstruktor: Initialisiert das Quiz mit Model, View und Hauptmenü-Controller
    public QuizController(QuizModel model, QuizView view, HauptmenueController contr) {
        this.model = model;
        this.view = view;
        this.contr = contr;

        // ActionListener für den "Eingabe bestätigen"-Button
        view.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSubmission(); // Verarbeitet die Nutzereingabe
            }
        });

        // ActionListener für den "Quiz wiederholen"-Button
        view.getRetryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartQuiz(); // Startet das Quiz neu
            }
        });

        // ActionListener für den "Hauptmenü"-Button
        view.getMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose(); // Schließt das Quiz-Fenster
                contr.getView().setVisible(true); // Öffnet das Hauptmenü wieder
            }
        });

        updateView(); // Zeigt die erste Frage an
    }

    // Methode zur Verarbeitung der Nutzereingabe
    private void handleAnswerSubmission() {
        String answer = view.getAnswer(); // Holt die Antwort aus dem Eingabefeld
        model.checkAnswer(answer); // Prüft die Antwort über das Modell

        if (model.hasMoreQuestions()) { // Falls noch Fragen übrig sind
            view.clearAnswerField(); // Leert das Eingabefeld für die nächste Frage
            updateView(); // Zeigt die nächste Frage an
        } else {
            showResult(); // Zeigt das Endergebnis, wenn keine Fragen mehr übrig sind
        }
    }

    // Aktualisiert die GUI mit der aktuellen Frage
    private void updateView() {
        view.setQuestion(model.getCurrentQuestion().getQuestion()); // Holt die aktuelle Frage aus dem Model
        view.clearAnswerField(); // Löscht die Eingabe aus dem Textfeld
    }

    // Zeigt das Endergebnis des Quiz an
    private void showResult() {
        int score = model.getScore(); // Holt die Punktzahl
        int totalQuestions = model.getTotalQuestions(); // Holt die Gesamtanzahl der Fragen

        view.clearLayout(); // Entfernt bestehende GUI-Elemente
        JPanel resultPanel = new JPanel(); // Neues Panel für das Ergebnis
        JLabel resultLabel = new JLabel(
                "<html>Ergebnis: " + score + "/" + totalQuestions + "<br>Quiz beendet!</html>",
                SwingConstants.CENTER
        ); // Erstellt ein Label mit der Punktzahl
        resultPanel.add(resultLabel); // Fügt das Label ins Panel ein
        view.add(resultPanel); // Fügt das Panel zur View hinzu
        view.getRetryButton().setVisible(true); // Zeigt den "Quiz wiederholen"-Button
        view.getMainMenuButton().setVisible(true); // Zeigt den "Hauptmenü"-Button
        view.revalidate(); // Aktualisiert das Layout
        view.repaint(); // Erzwingt eine Neuzeichnung der GUI
    }

    // Startet das Quiz neu
    private void restartQuiz() {
        model.resetQuiz(); // Setzt das Modell zurück (Punktzahl & Index)
        view.dispose(); // Schließt das aktuelle Fenster
        // Erstellt eine neue Instanz des QuizControllers mit neuen Model- und View-Objekten
        new QuizController(
                new QuizModel(contr.getVerzeichnis() + "\\frage.txt", contr.getVerzeichnis() + "\\antwort.txt"), new QuizView(), contr);
    }
}
