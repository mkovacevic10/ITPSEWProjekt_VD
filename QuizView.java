import javax.swing.*;
import java.awt.*;

public class QuizView extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JButton retryButton;
    private JButton mainMenuButton;
    private JPanel mainPanel;

    public QuizView() {
        setTitle("Quiz");
        setSize(700, 600);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(6, 1, 0, 10));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 24));
        submitButton = new JButton("Eingabe bestätigen");
        retryButton = new JButton("Quiz wiederholen");
        mainMenuButton = new JButton("Hauptmenü");

        retryButton.setVisible(false);
        mainMenuButton.setVisible(false);

        mainPanel.add(questionLabel);
        mainPanel.add(answerField);
        mainPanel.add(submitButton);
        mainPanel.add(retryButton);
        mainPanel.add(mainMenuButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(mainPanel, gbc);

        setVisible(true);
    }

    public void clearLayout() {
        mainPanel.removeAll();
    }

    public void setQuestion(String question) {
        questionLabel.setText(question);
    }

    public void clearAnswerField() {
        answerField.setText("");
    }

    public String getAnswer() {
        return answerField.getText();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getRetryButton() {
        return retryButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}