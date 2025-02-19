public class KarteikartenModel {
    private String[] karteikarten = {"Karteikarte 1", "Karteikarte 2"};
    private int currentIndex = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    public String getCurrentFlashcard() {
        return karteikarten[currentIndex];
    }

    public void nextFlashcard() {
        currentIndex++;
        if (currentIndex >= karteikarten.length) {
            currentIndex = 0;
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
