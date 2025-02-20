import java.util.ArrayList;
import java.util.List;

public class KarteikartenModel {
    private List<String> karteikarten = new ArrayList<>();
    private List<String> loesungen = new ArrayList<>();
    private int currentIndex = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    public KarteikartenModel() {
        addFlashcard("Karteikarte 1", "Lösung 1");
        addFlashcard("Karteikarte 2", "Lösung 2");
    }

    public String getCurrentFlashcard() {
        return karteikarten.get(currentIndex);
    }

    public String getSolutionForCurrentCard() {
        return loesungen.get(currentIndex).isEmpty() ? "Keine Lösung vorhanden" : loesungen.get(currentIndex);
    }

    public void nextFlashcard() {
        currentIndex++;
        if (currentIndex >= karteikarten.size()) {
            currentIndex = 0;
        }
    }

    public void addFlashcard(String card, String solution) {
        karteikarten.add(card);
        loesungen.add(solution);
    }

    public void addSolutionToLastCard(String solution) {
        if (!karteikarten.isEmpty()) {
            loesungen.set(loesungen.size() - 1, solution);
        }
    }

    public void incrementCorrect() {
        correctAnswers++;
    }

    public void incrementWrong() {
        wrongAnswers++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }
}