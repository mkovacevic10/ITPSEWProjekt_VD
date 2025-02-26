import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class HauptmenueController {
    private HauptmenueView view;
    private HauptmenueModel model;

    public HauptmenueController(HauptmenueView view, HauptmenueModel model) {
        this.view = view;
        this.model = model;
        HauptmenueController contr = this;

        // Lernkartei-Button Listener
        this.view.addLernkarteiListener(e -> {
            view.showMessage(model.getLernkarteiMessage());
            view.nextProgram();
            new KarteikartenController(new KarteikartenModel(), new KarteikartenView(), contr);
        });

        // Quiz-Button Listener
        this.view.addQuizListener(e -> {
            view.showMessage(model.getQuizMessage());
            view.nextProgram();
            new QuizController(new QuizModel(List.of(
                    new Fragen("Was ist 2+2?", "4"),
                    new Fragen("Was ist die Hauptstadt von Frankreich?", "Paris")
            )), new QuizView(), contr);
        });

        // Spiel-Button Listener
        this.view.addSpielListener(e -> {
            view.showMessage(model.getSpielMessage());
            view.nextProgram();
            String[] questionAndAnswer = getRandomQuestionAndAnswer("itp-programm\\questions.txt", "itp-programm\\answers.txt");
            new Spiel(questionAndAnswer[0], questionAndAnswer[1], contr); // Frage und Antwort werden übergeben
        });

        // Datei-Button Listener
        this.view.addDateiListener(e -> {
            view.showMessage(model.getDateiMessage());
        });

        // Schließen-Button Listener
        this.view.addSchließenListener(e -> {
            view.closeProgram();
        });
    }

    public HauptmenueView getView() {
        return view;
    }

    // Methode, um eine zufällige Frage und Antwort aus den Dateien zu holen
    public String[] getRandomQuestionAndAnswer(String questionFile, String answerFile) {
        try {
            List<String> questions = Files.readAllLines(Paths.get(questionFile));
            List<String> answers = Files.readAllLines(Paths.get(answerFile));
            if (!questions.isEmpty() && !answers.isEmpty() && questions.size() == answers.size()) {
                int index = new Random().nextInt(questions.size());
                return new String[]{questions.get(index), answers.get(index)};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"Frage nicht verfügbar", "DEFAULT"}; // Falls Datei nicht gelesen werden kann, Standard setzen
    }

    public static void main(String[] args) {
        HauptmenueModel model = new HauptmenueModel();
        HauptmenueView view = new HauptmenueView();
        new HauptmenueController(view, model);
        view.setVisible(true);
    }
}
