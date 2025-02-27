import java.io.*; // Import für Dateioperationen
import java.nio.file.Files; // Import für das Lesen von Dateien
import java.nio.file.Paths; // Import für die Arbeit mit Dateipfaden
import java.util.List; // Import für Listen
import java.util.Random; // Import für Zufallszahlen

// Der HauptmenueController verwaltet das Hauptmenü der Anwendung
public class HauptmenueController {
    private HauptmenueView view; // Die Benutzeroberfläche für das Hauptmenü
    private HauptmenueModel model; // Das Modell für das Hauptmenü (Daten und Logik)
    private String verzeichnis; // Verzeichnis, in dem sich die Dateien befinden

    // Konstruktor: Initialisiert das Hauptmenü mit View und Model
    public HauptmenueController(HauptmenueView view, HauptmenueModel model) {
        this.view = view;
        this.model = model;
        verzeichnis = "itp-programm"; // Standardverzeichnis für Dateien
        HauptmenueController contr = this; // Referenz auf sich selbst
        SpeichernLaden sl = new SpeichernLaden(contr); // Initialisiert das Speichern/Laden-Modul

        // Listener für den "Lernkartei"-Button
        //Wenn der Button geklickt wird (e ist das ActionEvent), dann:
        //Zeigt die View eine Nachricht an (view.showMessage(...))
        //Wechselt zur nächsten Ansicht (view.nextProgram())
        //Erstellt eine neue Instanz des Karteikarten-Controllers
        this.view.addLernkarteiListener(e -> {
            view.showMessage(model.getLernkarteiMessage()); // Zeigt eine Nachricht für Lernkartei an
            view.nextProgram(); // Wechselt zum nächsten Programmschritt
            new KarteikartenController(new KarteikartenModel(), new KarteikartenView(), contr, this.verzeichnis); // Erstellt eine neue Karteikarten-Instanz
        });

        // Listener für den "Quiz"-Button
        this.view.addQuizListener(e -> {
            view.showMessage(model.getQuizMessage()); // Zeigt eine Nachricht für das Quiz an
            view.nextProgram(); // Wechselt zum nächsten Programmschritt
            new QuizController(
                    new QuizModel(verzeichnis + "\\questions.txt", verzeichnis + "\\answers.txt"),
                    new QuizView(),
                    this
            ); // Erstellt eine neue Quiz-Instanz
        });

        // Listener für den "Spiel"-Button
        this.view.addSpielListener(e -> {
            view.showMessage(model.getSpielMessage()); // Zeigt eine Nachricht für das Spiel an
            view.nextProgram(); // Wechselt zum nächsten Programmschritt
            String[] questionAndAnswer = getRandomQuestionAndAnswer(verzeichnis + "\\questions.txt", verzeichnis + "\\answers.txt"); // Holt eine zufällige Frage
            new Spiel(questionAndAnswer[0], questionAndAnswer[1], contr); // Erstellt eine neue Spiel-Instanz
        });

        // Listener für den "Datei"-Button (zum Speichern/Laden von Dateien)
        this.view.addDateiListener(e -> {
            view.showMessage(model.getDateiMessage()); // Zeigt eine Nachricht für die Dateioptionen an
            view.nextProgram(); // Wechselt zum nächsten Programmschritt
            sl.visibilty(true); // Zeigt das Speicher/Laden-Fenster an
        });

        // Listener für den "Schließen"-Button (zum Beenden des Programms)
        this.view.addSchließenListener(e -> {
            view.closeProgram(); // Schließt das Programm
        });
    }

    // Getter für die Hauptmenü-View
    public HauptmenueView getView() {
        return view;
    }

    // Methode, um eine zufällige Frage und die zugehörige Antwort aus den Dateien zu holen
    public String[] getRandomQuestionAndAnswer(String questionFile, String answerFile) {
        try {
            List<String> questions = Files.readAllLines(Paths.get(questionFile)); // Liest alle Fragen aus der Datei
            List<String> answers = Files.readAllLines(Paths.get(answerFile)); // Liest alle Antworten aus der Datei
            if (!questions.isEmpty() && !answers.isEmpty() && questions.size() == answers.size()) { // Prüft, ob Dateien gültige Inhalte haben
                int index = new Random().nextInt(questions.size()); // Wählt zufällig eine Frage aus
                return new String[]{questions.get(index), answers.get(index)}; // Gibt die Frage-Antwort-Paarung zurück
            }
        } catch (IOException e) {
            e.printStackTrace(); // Gibt Fehler aus, falls Dateien nicht geladen werden können
        }
        return new String[]{"Frage nicht verfügbar", "DEFAULT"}; // Falls ein Fehler auftritt, wird eine Standardantwort zurückgegeben
    }

    // Setter für das Verzeichnis (Dateipfad)
    public void setVerzeichnis(String v) {
        this.verzeichnis = v;
    }

    // Getter für das Verzeichnis
    public String getVerzeichnis() {
        return verzeichnis;
    }

    // Die Main-Methode startet das Hauptmenü der Anwendung
    public static void main(String[] args) {
        HauptmenueModel model = new HauptmenueModel(); // Erstellt das Hauptmenü-Modell
        HauptmenueView view = new HauptmenueView(); // Erstellt die Hauptmenü-View
        new HauptmenueController(view, model); // Erstellt den Controller
        view.setVisible(true); // Zeigt die Benutzeroberfläche an
    }
}
