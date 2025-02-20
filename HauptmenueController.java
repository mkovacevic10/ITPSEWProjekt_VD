import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class HauptmenueController {
    public HauptmenueView getView() {
        return view;
    }

    public HauptmenueModel getModel() {
        return model;
    }

    private HauptmenueView view;
    private HauptmenueModel model;
    private Spiel spiel;

    public HauptmenueController(HauptmenueView view, HauptmenueModel model) {
        this.view = view;
        this.model = model;
        HauptmenueController contr = this;
        KarteikartenModel modelk = new KarteikartenModel();
        KarteikartenView viewK = new KarteikartenView();
        QuizView viewq = new QuizView();
        List<Fragen> questions = Arrays.asList(
                new Fragen("Was ist 2+2?", "4"),
                new Fragen("Was ist die Hauptstadt von Frankreich?", "Paris")
        );
        QuizModel modelq = new QuizModel(questions);


        // Verbinde die Buttons mit den entsprechenden Aktionen
        this.view.addLernkarteiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showMessage(model.getLernkarteiMessage());
                view.nextProgram();
                new KarteikartenController(modelk,viewK,contr);
            }
        });

        this.view.addQuizListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showMessage(model.getQuizMessage());
                view.nextProgram();
                new QuizController(modelq, viewq, contr);
            }
        });

        this.view.addSpielListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showMessage(model.getSpielMessage());
                view.nextProgram();
                new Spiel("ayri", contr);


            }
        });

        this.view.addDateiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showMessage(model.getDateiMessage());
            }
        });

        this.view.addSchließenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.closeProgram();
            }
        });
    }
    public static void main(String[] args) {
        HauptmenueModel model = new HauptmenueModel();
        HauptmenueView view = new HauptmenueView();
        new HauptmenueController(view, model);
        view.setVisible(true);
    }
}
