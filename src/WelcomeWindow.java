import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow {

    private JFrame mainFrame;
    private HelperInterface helperInterface;

    public interface HelperInterface {
        void openPlayWindow(String playerName, int level);
    }

    public void setHelperInterface(HelperInterface helperInterface) {
        this.helperInterface = helperInterface;
    }

    public void startWindow() {
        mainFrame = new JFrame("Pirate Hunt");
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));


        JLabel welcomeMessage = new JLabel("Welcome in Pirate Hunt", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.PLAIN, 30));
        welcomeMessage.setForeground(Color.RED);

        JLabel playerName = new JLabel("Enter your name: ", SwingConstants.CENTER);
        playerName.setFont(new Font("Serif", Font.PLAIN, 18));

        JTextField playerNameInput = new JTextField();
        playerNameInput.setFont(new Font("Serif", Font.PLAIN, 20));
        playerNameInput.setHorizontalAlignment(JTextField.CENTER);


        JLabel level = new JLabel("Choose the level: ", SwingConstants.CENTER);
        playerName.setFont(new Font("Serif", Font.PLAIN, 18));

        String[] levelsArray = { "1", "2", "3"};
        JComboBox levelsList = new JComboBox(levelsArray);
        levelsList.setSelectedIndex(0);
        levelsList.setFont(new Font("Serif", Font.PLAIN, 22));

        JButton startNewGame = new JButton("Start new game");
        startNewGame.setBackground(new Color(77, 148, 255));

        startNewGame.addActionListener(e -> {
            if (!playerNameInput.getText().trim().equals("")) {
                if (helperInterface != null) {
                    helperInterface.openPlayWindow(playerNameInput.getText().trim(), levelsList.getSelectedIndex() + 1);
                }
            }
        });


        mainPanel.add(welcomeMessage);
        mainPanel.add(playerName);
        mainPanel.add(playerNameInput);
        mainPanel.add(level);
        mainPanel.add(levelsList);
        mainPanel.add(startNewGame);


        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);

    }

    public void setWindowVisibility(boolean visibility) {
        mainFrame.setVisible(visibility);
    }
}
