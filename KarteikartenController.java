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
    private KarteikartenModel model;
    private KarteikartenView view;
    private boolean showSolution = false;
    private List<String> questions;
    private List<String> answers;
    private Random random;
    private String currentQuestion; // Speichert die aktuelle Frage
    private HauptmenueController ctrl;

    public KarteikartenController(KarteikartenModel model, KarteikartenView view, HauptmenueController contr, String verz) {
        this.model = model;
        this.view = view;
        this.random = new Random();
        this.ctrl = contr;

        // Lade Fragen und Antworten
        loadQuestionsAndAnswers();

        // Wähle eine zufällige Frage und zeige sie an
        currentQuestion = getRandomQuestion(); // Speichern der aktuellen Frage
        view.setCardText(currentQuestion); // Die Frage an das View übergeben

        view.addNextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentQuestion = getRandomQuestion(); // Neue Frage wählen
                view.setCardText(currentQuestion); // Neue Frage anzeigen
                showSolution = false;
            }
        });

        view.addExitListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Ergebnis(model.getCorrectAnswers(), model.getWrongAnswers(), contr).setVisible(true);
                view.dispose();
                model.reset();
            }
        });

        view.addCorrectListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!showSolution) {
                    model.incrementCorrect();
                    view.showSolution(getAnswerForCurrentQuestion());
                    showSolution = true;
                }
            }
        });

        view.addWrongListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!showSolution) {
                    model.incrementWrong();
                    view.hideSolution();
                    showSolution = true;
                }
            }
        });

        view.addAddCardListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newQuestion = JOptionPane.showInputDialog(view, "Neue Frage eingeben:");
                if (newQuestion != null && !newQuestion.isEmpty()) {
                    String newAnswer = JOptionPane.showInputDialog(view, "Lösung für die neue Frage eingeben:");
                    if (newAnswer != null && !newAnswer.isEmpty()) {
                        // Füge die neue Frage und Lösung der Liste hinzu
                        questions.add(newQuestion);
                        answers.add(newAnswer);
                        model.addFlashcard(newQuestion, newAnswer); // Optional, falls das Modell eine Methode dafür hat
                        JOptionPane.showMessageDialog(view, "Karteikarte hinzugefügt!");
                    } else {
                        JOptionPane.showMessageDialog(view, "Bitte geben Sie eine Lösung ein.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Bitte geben Sie eine Frage ein.");
                }
            }
        });

        view.addSolutionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String solution = JOptionPane.showInputDialog(view, "Lösung für die letzte hinzugefügte Karteikarte eingeben:");
                if (solution != null && !solution.isEmpty()) {
                    model.addSolutionToLastCard(solution);
                    JOptionPane.showMessageDialog(view, "Lösung hinzugefügt!");
                }
            }
        });

        view.setVisible(true);
    }

    private void loadQuestionsAndAnswers() {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        try {
            BufferedReader questionReader = new BufferedReader(new FileReader(ctrl.getVerzeichnis()+"\\terms.txt"));
            BufferedReader answerReader = new BufferedReader(new FileReader(ctrl.getVerzeichnis()+"\\definitions.txt"));
            String questionLine, answerLine;

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

    private String getRandomQuestion() {
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }

    private String getAnswerForCurrentQuestion() {
        // Finde die Antwort basierend auf der aktuellen Frage
        int index = questions.indexOf(currentQuestion); // Verwende die gespeicherte Frage
        if (index != -1) {
            return answers.get(index);
        }
        return "";
    }
}

