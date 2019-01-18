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
        mainPanel.setLayout(new GridLayout(8, 1));


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

        JButton getHelpButton = new JButton("Help");

        startNewGame.addActionListener(e -> {
            if (!playerNameInput.getText().trim().equals("")) {
                if (helperInterface != null) {
                    helperInterface.openPlayWindow(playerNameInput.getText().trim(), levelsList.getSelectedIndex() + 1);
                }
            }
        });

        getHelpButton.addActionListener(e -> {
            JFrame help = new JFrame("Help");
            help.setSize(850, 750);
            help.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            JPanel helpPanel = new JPanel();
            JLabel gameHelp = new JLabel();
            gameHelp.setText("" +
                    "<html>" +
                    "<h1>Upustvo</h1>" +
                    "<h2>1. Igra se igra na matrici 15x15 sa preprekama.</h2>" +
                    "<h2>2. Cilj igrača je doći iz jednog polja u drugo. </h2>" +
                    "<h2>3. Gusari pokušavaju uhvatiti igrača.</h2>" +
                    "<h2>4. Ako gusar udari u prepreku, nestaje.</h2>" +
                    "<h2>5. Ako se dva gusara sudare, nastane nova prepreka.</h2>" +
                    "<h2>6. Brod se kreće pomoću keyboard</h2>" +
                    "</html>"
            );


            helpPanel.add(gameHelp);
            help.add(helpPanel);
            help.setResizable(false);
            help.setVisible(true);
        });


        mainPanel.add(welcomeMessage);
        mainPanel.add(playerName);
        mainPanel.add(playerNameInput);
        mainPanel.add(level);
        mainPanel.add(levelsList);
        mainPanel.add(startNewGame);
        mainPanel.add(getHelpButton);


        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);

    }

    public void setWindowVisibility(boolean visibility) {
        mainFrame.setVisible(visibility);
    }
}
