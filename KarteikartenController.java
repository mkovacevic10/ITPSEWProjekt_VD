import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KarteikartenController {
    private KarteikartenModel model;
    private KarteikartenView view;

    public KarteikartenController(KarteikartenModel model, KarteikartenView view, HauptmenueController contr) {
        this.model = model;
        this.view = view;

        view.setCardText(model.getCurrentFlashcard());

        view.addNextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.nextFlashcard();
                view.setCardText(model.getCurrentFlashcard());
            }
        });

        view.addExitListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Ergebnis(model.getCorrectAnswers(), model.getWrongAnswers(), contr).setVisible(true);
                view.dispose();
            }
        });

        view.addCorrectListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.incrementCorrect();
                model.nextFlashcard();
                view.setCardText(model.getCurrentFlashcard());
            }
        });

        view.addWrongListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.incrementWrong();
                model.nextFlashcard();
                view.setCardText(model.getCurrentFlashcard());
            }
        });
        view.setVisible(true);
    }
}