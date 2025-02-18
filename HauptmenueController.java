import java.awt.event.*;

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

        // Verbinde die Buttons mit den entsprechenden Aktionen
        this.view.addLernkarteiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showMessage(model.getLernkarteiMessage());
            }
        });

        this.view.addQuizListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showMessage(model.getQuizMessage());
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
        // Erstelle das Model und die View
        HauptmenueModel model = new HauptmenueModel();
        HauptmenueView view = new HauptmenueView();

        // Erstelle den Controller und übergebe die View und das Model
        new HauptmenueController(view, model);

        // Mache die View sichtbar
        view.setVisible(true);
    }
}
