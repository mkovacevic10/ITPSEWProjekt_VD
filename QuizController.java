import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizController {
    private QuizModel model;
    private QuizView view;
    private HauptmenueController contr;

    public QuizController(QuizModel model, QuizView view, HauptmenueController contr) {
        this.model = model;
        this.view = view;
        this.contr = contr;

        view.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSubmission();
            }
        });

        view.getRetryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartQuiz();
            }
        });

        view.getMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                contr.getView().setVisible(true);
            }
        });

        updateView();
    }

    private void handleAnswerSubmission() {
        String answer = view.getAnswer();
        model.checkAnswer(answer);

        if (model.hasMoreQuestions()) {
            view.clearAnswerField();
            updateView();
        } else {
            showResult();
        }
    }

    private void updateView() {
        view.setQuestion(model.getCurrentQuestion().getQuestion());
        view.clearAnswerField();
    }

    private void showResult() {
        int score = model.getScore();
        int totalQuestions = model.getTotalQuestions();

        view.clearLayout();
        JPanel resultPanel = new JPanel();
        JLabel resultLabel = new JLabel("<html>Ergebnis: " + score + "/" + totalQuestions + "<br>Quiz beendet!</html>", SwingConstants.CENTER);
        resultPanel.add(resultLabel);
        view.add(resultPanel);
        view.getRetryButton().setVisible(true);
        view.getMainMenuButton().setVisible(true);
        view.revalidate();
        view.repaint();
    }

    private void restartQuiz() {
        model.resetQuiz();
        view.dispose();
        new QuizController(new QuizModel(contr.getVerzeichnis()+"\\frage.txt", contr.getVerzeichnis()+"\\antwort.txt"), new QuizView(), contr);
    }
}