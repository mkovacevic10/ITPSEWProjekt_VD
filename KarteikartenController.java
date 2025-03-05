import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KarteikartenController {
    private KarteikartenModel model; // Das Modell speichert den Fortschritt der Lernkarten
    private KarteikartenView view; // Die View zeigt die Lernkarten an
    private boolean showSolution = false; // Gibt an, ob die Lösung angezeigt wird
    private List<String> questions; // Liste der Fragen
    private List<String> answers; // Liste der Antworten
    private Random random; // Zufallszahlengenerator für zufällige Karten
    private String currentQuestion; // Speichert die aktuell angezeigte Frage
    private HauptmenueController ctrl; // Hauptmenü-Controller zur Navigation

    // Konstruktor: Initialisiert das Modell, die View und lädt die Fragen
    public KarteikartenController(KarteikartenModel model, KarteikartenView view, HauptmenueController contr, String verz) {
        this.model = model;
        this.view = view;
        this.random = new Random();
        this.ctrl = contr;

        // Lade Fragen und Antworten aus Dateien
        loadQuestionsAndAnswers();

        // Wähle eine zufällige Frage und zeige sie an
        currentQuestion = getRandomQuestion();
        view.setCardText(currentQuestion);

        // Listener für den "Nächste Karte"-Button
        view.addNextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentQuestion = getRandomQuestion(); // Neue Frage auswählen
                view.setCardText(currentQuestion); // Neue Frage anzeigen
                showSolution = false; // Lösung wird zurückgesetzt
            }
        });

        // Listener für den "Beenden"-Button
        view.addExitListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Ergebnis(model.getCorrectAnswers(), model.getWrongAnswers(), contr).setVisible(true); // Ergebnisse anzeigen
                view.dispose(); // Fenster schließen
                model.reset(); // Fortschritt zurücksetzen
            }
        });

        // Listener für den "Richtig"-Button
        view.addCorrectListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!showSolution) { // Nur ausführen, wenn die Lösung noch nicht angezeigt wurde
                    model.incrementCorrect(); // Zähler für richtige Antworten erhöhen
                    view.showSolution(getAnswerForCurrentQuestion()); // Lösung anzeigen
                    showSolution = true;
                }
            }
        });

        // Listener für den "Falsch"-Button
        view.addWrongListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!showSolution) { // Nur ausführen, wenn die Lösung noch nicht angezeigt wurde
                    model.incrementWrong(); // Zähler für falsche Antworten erhöhen
                    view.hideSolution(); // Lösung nicht anzeigen
                    showSolution = true;
                }
            }
        });

        // Listener für den "Karte hinzufügen"-Button
        view.addAddCardListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newQuestion = JOptionPane.showInputDialog(view, "Neue Frage eingeben:");
                if (newQuestion != null && !newQuestion.isEmpty()) {
                    String newAnswer = JOptionPane.showInputDialog(view, "Lösung für die neue Frage eingeben:");
                    if (newAnswer != null && !newAnswer.isEmpty()) {
                        // Neue Frage und Antwort zur Liste hinzufügen
                        questions.add(newQuestion);
                        answers.add(newAnswer);
                        model.addFlashcard(newQuestion, newAnswer); // Falls das Modell diese Methode hat
                        JOptionPane.showMessageDialog(view, "Karteikarte hinzugefügt!");
                    } else {
                        JOptionPane.showMessageDialog(view, "Bitte geben Sie eine Lösung ein.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Bitte geben Sie eine Frage ein.");
                }
            }
        });

        view.setVisible(true); // Die View anzeigen
    }

    // Methode zum Laden der Fragen und Antworten aus den Dateien
    private void loadQuestionsAndAnswers() {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        try {
            // Dateien für Fragen und Antworten öffnen
            BufferedReader questionReader = new BufferedReader(new FileReader(ctrl.getVerzeichnis() + "\\terms.txt"));
            BufferedReader answerReader = new BufferedReader(new FileReader(ctrl.getVerzeichnis() + "\\definitions.txt"));
            String questionLine, answerLine;

            // Zeilenweise die Fragen und Antworten einlesen
            while ((questionLine = questionReader.readLine()) != null && (answerLine = answerReader.readLine()) != null) {
                questions.add(questionLine);
                answers.add(answerLine);
            }
            questionReader.close();
            answerReader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Fehler beim Laden der Fragen und Antworten.");
            e.printStackTrace();
        }
    }

    // Methode zum Zufälligen Abrufen einer Frage aus der Liste
    private String getRandomQuestion() {
        int index = random.nextInt(questions.size()); // Zufälligen Index wählen
        return questions.get(index); // Frage zurückgeben
    }

    // Methode zur Ermittlung der Antwort zur aktuellen Frage
    private String getAnswerForCurrentQuestion() {
        int index = questions.indexOf(currentQuestion); // Index der aktuellen Frage finden
        if (index != -1) {
            return answers.get(index); // Die passende Antwort zurückgeben
        }
        return ""; // Falls keine Antwort gefunden wird
    }
}