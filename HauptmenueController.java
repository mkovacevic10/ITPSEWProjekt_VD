import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class HauptmenueController {
    private HauptmenueView view;
    private HauptmenueModel model;
    private String verzeichnis;

    public HauptmenueController(HauptmenueView view, HauptmenueModel model) {
        this.view = view;
        this.model = model;
        verzeichnis = "itp-programm";
        HauptmenueController contr = this;
        SpeichernLaden sl = new SpeichernLaden(contr);

        // Lernkartei-Button Listener
        this.view.addLernkarteiListener(e -> {
            view.showMessage(model.getLernkarteiMessage());
            view.nextProgram();
            new KarteikartenController(new KarteikartenModel(), new KarteikartenView(), contr, this.verzeichnis);
        });

        // Quiz-Button Listener
        this.view.addQuizListener(e -> {
            view.showMessage(model.getQuizMessage());
            view.nextProgram();
            new QuizController(new QuizModel(verzeichnis+"\\questions.txt", verzeichnis+"\\answers.txt"), new QuizView(), this);
        });

        // Spiel-Button Listener
        this.view.addSpielListener(e -> {
            view.showMessage(model.getSpielMessage());
            view.nextProgram();
            String[] questionAndAnswer = getRandomQuestionAndAnswer(verzeichnis+"\\questions.txt", verzeichnis+"\\answers.txt");
            new Spiel(questionAndAnswer[0], questionAndAnswer[1], contr);
        });

        // Datei-Button Listener
        this.view.addDateiListener(e -> {
            view.showMessage(model.getDateiMessage());
            view.nextProgram();
            sl.visibilty(true);
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
    public void setVerzeichnis(String v) {
        this.verzeichnis=v;
    }

    public String getVerzeichnis() {
        return verzeichnis;
    }

    public static void main(String[] args) {
        HauptmenueModel model = new HauptmenueModel();
        HauptmenueView view = new HauptmenueView();
        new HauptmenueController(view, model);
        view.setVisible(true);
    }
}
