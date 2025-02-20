import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class QuizController {
    private QuizModel model;
    private QuizView view;
    HauptmenueController contr;

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

        // Enter-Taste zum Einloggen der Antwort hinzufügen
        view.getAnswerField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleAnswerSubmission();
                }
            }
        });

        updateView();
    }

    private void handleAnswerSubmission() {
        String answer = view.getAnswer();
        boolean isCorrect = model.checkAnswer(answer);
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
        JPanel resultPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        resultPanel.setPreferredSize(new Dimension(600, 400));
        JLabel resultLabel = null;
        int haelfte = totalQuestions / 2;

        if (score == totalQuestions) {
            resultLabel = new JLabel("<html>Herzlichen Glückwunsch, Profi!<br>Alles richtig, respect!<br>Ergebnis: " + score + "/" + totalQuestions + "</html>", SwingConstants.CENTER);
        } else if (score >= haelfte && score != totalQuestions) {
            resultLabel = new JLabel("<html>Da ist noch Luft nach oben! :)<br>Versuch es gerne nochmal!<br>Ergebnis: " + score + "/" + totalQuestions + "</html>", SwingConstants.CENTER);
        } else if (score < haelfte || score == 0) {
            resultLabel = new JLabel("<html>Leider nicht geschafft :( <br>Versuch es nochmal!<br>Ergebnis: " + score + "/" + totalQuestions + "</html>", SwingConstants.CENTER);
        }

        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        if (score == 0 || score != totalQuestions) {
            view.getRetryButton().setVisible(true);
            view.getMainMenuButton().setVisible(true);
        } else {
            view.getRetryButton().setVisible(false);
            view.getMainMenuButton().setVisible(true);
        }

        resultPanel.add(resultLabel);
        resultPanel.add(view.getRetryButton());
        resultPanel.add(view.getMainMenuButton());

        view.add(resultPanel);
        view.revalidate();
        view.repaint();
    }

    private void restartQuiz() {
        List<Fragen> questions = Arrays.asList(
                new Fragen("Was ist 2+2?", "4"),
                new Fragen("Was ist die Hauptstadt von Frankreich?", "Paris")
        );
        model = new QuizModel(questions);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.dispose();
                QuizView newView = new QuizView();
                new QuizController(model, newView, contr);
            }
        });
    }

    private void goToMainMenu() {
        // Keine zusätzliche Logik erforderlich, da der Button keine Funktion haben soll
    }
}