public class Fragen {
    private String question;
    private String correctAnswer;

    public Fragen(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isCorrect(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}