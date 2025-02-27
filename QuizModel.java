import java.io.IOException;          // Import für mögliche Datei-I/O-Ausnahmen
import java.nio.file.Files;          // Import für das Lesen von Dateien
import java.nio.file.Paths;          // Import für das Arbeiten mit Dateipfaden
import java.util.*;                  // Importiert alle Utility-Klassen (z.B. List, ArrayList, Collections)

// Die Klasse QuizModel verwaltet die Fragen und den Spielfortschritt
public class QuizModel {
    private List<Fragen> questions;    // Liste der Fragen-Objekte
    private int currentQuestionIndex;  // Index der aktuellen Frage
    private int score;                 // Punktestand des Spielers

    // Konstruktor: Initialisiert das Quiz mit Fragen und Antworten aus Dateien
    public QuizModel(String questionFile, String answerFile) {
        this.questions = loadQuestionsFromFiles(questionFile, answerFile); // Lädt Fragen und Antworten
        this.currentQuestionIndex = 0;  // Setzt den Index auf die erste Frage
        this.score = 0;                 // Punktestand auf 0 setzen
    }

    // Methode zum Laden der Fragen aus den angegebenen Dateien
    private List<Fragen> loadQuestionsFromFiles(String questionFile, String answerFile) {
        List<Fragen> loadedQuestions = new ArrayList<>();
        try {
            // Liest alle Zeilen aus den Datei-Pfaden
            List<String> questionLines = Files.readAllLines(Paths.get(questionFile));
            List<String> answerLines = Files.readAllLines(Paths.get(answerFile));

            // Begrenzung auf maximal 10 Fragen oder die Anzahl der verfügbaren Fragen (falls < 10)
            int maxQuestions = Math.min(10, Math.min(questionLines.size(), answerLines.size()));

            // Erstelle eine Liste mit Indizes von 0 bis maxQuestions-1
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < maxQuestions; i++) {
                indices.add(i);
            }
            Collections.shuffle(indices); // Durchmischen der Fragen für zufällige Reihenfolge

            // Fragen-Objekte mit zufällig ausgewählten Fragen und Antworten erstellen
            for (int i = 0; i < maxQuestions; i++) {
                int idx = indices.get(i); // Holt zufälligen Index aus der gemischten Liste
                loadedQuestions.add(new Fragen(questionLines.get(idx), answerLines.get(idx)));
            }
        } catch (IOException e) { // Falls eine Datei nicht gelesen werden kann
            e.printStackTrace();
        }
        return loadedQuestions; // Gibt die geladene Fragenliste zurück
    }

    // Gibt die aktuelle Frage zurück
    public Fragen getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    // Überprüft, ob die gegebene Antwort korrekt ist
    public boolean checkAnswer(String answer) {
        boolean isCorrect = getCurrentQuestion().isCorrect(answer); // Vergleicht die Antwort
        if (isCorrect) {
            score++; // Erhöht den Punktestand, falls die Antwort richtig ist
        }
        currentQuestionIndex++; // Wechselt zur nächsten Frage
        return isCorrect; // Gibt zurück, ob die Antwort richtig war
    }

    // Prüft, ob es noch weitere Fragen gibt
    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    // Gibt den aktuellen Punktestand zurück
    public int getScore() {
        return score;
    }

    // Gibt die Gesamtanzahl der geladenen Fragen zurück
    public int getTotalQuestions() {
        return questions.size();
    }

    // Setzt das Quiz zurück (zurück auf die erste Frage und Score auf 0)
    public void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
    }
}
