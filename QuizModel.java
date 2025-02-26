import java.util.List;

public class QuizModel {
    private List<Fragen> questions;
    private int currentQuestionIndex;
    private int score;

    public QuizModel(List<Fragen> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public Fragen getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean checkAnswer(String answer) {
        boolean isCorrect = getCurrentQuestion().isCorrect(answer);
        if (isCorrect) {
            score++;
        }
        currentQuestionIndex++;
        return isCorrect;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
    }
}