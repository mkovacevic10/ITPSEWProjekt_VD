import java.util.ArrayList;
import java.util.List;

public class KarteikartenModel {
    private List<String> karteikarten = new ArrayList<>(); // Liste für die Fragen (Karteikarten)
    private List<String> loesungen = new ArrayList<>(); // Liste für die Lösungen der Karteikarten
    private int currentIndex = 0; // Speichert den Index der aktuell angezeigten Karteikarte
    private int correctAnswers = 0; // Anzahl der richtig beantworteten Karteikarten
    private int wrongAnswers = 0; // Anzahl der falsch beantworteten Karteikarten

    // Konstruktor: Initialisiert das Modell mit Standard-Karteikarten
    public KarteikartenModel() {
        initializeDefaultCards();
    }

    // Fügt Standard-Karteikarten hinzu und setzt Werte zurück
    public void initializeDefaultCards() {
        karteikarten.clear(); // Liste der Fragen zurücksetzen
        loesungen.clear(); // Liste der Lösungen zurücksetzen
        addFlashcard("Karteikarte 1", "Lösung 1"); // Beispielkarteikarte hinzufügen
        addFlashcard("Karteikarte 2", "Lösung 2");
        currentIndex = 0; // Index auf die erste Karteikarte setzen
        correctAnswers = 0; // Zähler für richtige Antworten zurücksetzen
        wrongAnswers = 0; // Zähler für falsche Antworten zurücksetzen
    }

    // Gibt die aktuell angezeigte Karteikarte (Frage) zurück
    public String getCurrentFlashcard() {
        return karteikarten.get(currentIndex);
    }

    // Gibt die Lösung zur aktuellen Karteikarte zurück
    public String getSolutionForCurrentCard() {
        return loesungen.get(currentIndex).isEmpty() ? "Keine Lösung vorhanden" : loesungen.get(currentIndex);
    }

    // Wechselt zur nächsten Karteikarte (zyklisch)
    public void nextFlashcard() {
        currentIndex++;
        if (currentIndex >= karteikarten.size()) {
            currentIndex = 0; // Falls das Ende erreicht ist, von vorne anfangen
        }
    }

    // Fügt eine neue Karteikarte mit der zugehörigen Lösung hinzu
    public void addFlashcard(String card, String solution) {
        karteikarten.add(card);
        loesungen.add(solution);
    }

    // Fügt eine Lösung zur zuletzt hinzugefügten Karteikarte hinzu oder ersetzt sie
    public void addSolutionToLastCard(String solution) {
        if (!karteikarten.isEmpty()) {
            loesungen.set(loesungen.size() - 1, solution);
        }
    }

    // Erhöht den Zähler für richtige Antworten
    public void incrementCorrect() {
        correctAnswers++;
    }

    // Erhöht den Zähler für falsche Antworten
    public void incrementWrong() {
        wrongAnswers++;
    }

    // Gibt die Anzahl der richtigen Antworten zurück
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    // Gibt die Anzahl der falschen Antworten zurück
    public int getWrongAnswers() {
        return wrongAnswers;
    }

    // Setzt das Modell zurück und lädt die Standard-Karteikarten
    public void reset() {
        initializeDefaultCards();
    }
}