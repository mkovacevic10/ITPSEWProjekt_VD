import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KarteikartenController {
    private KarteikartenModel model;
    private KarteikartenView view;
    private boolean showSolution = false;

    public KarteikartenController(KarteikartenModel model, KarteikartenView view, HauptmenueController contr) {
        this.model = model;
        this.view = view;

        view.setCardText(model.getCurrentFlashcard());

        view.addNextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.nextFlashcard();
                view.setCardText(model.getCurrentFlashcard());
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
                    view.showSolution(model.getSolutionForCurrentCard());
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
                String newCard = JOptionPane.showInputDialog(view, "Neue Karteikarte eingeben:");
                if (newCard != null && !newCard.isEmpty()) {
                    model.addFlashcard(newCard, "");
                    JOptionPane.showMessageDialog(view, "Karteikarte hinzugefügt! Bitte fügen Sie eine Lösung hinzu.");
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
}