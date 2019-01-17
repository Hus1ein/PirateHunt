import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    private JFrame mainFrame;
    private JFrame help;
    public void startWindow() {
        mainFrame = new JFrame("Pirate Hunt");
        mainFrame.setSize(600, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));


        JLabel welcomeMessage = new JLabel("Welcome in Pirate Hunt", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.PLAIN, 40));
        welcomeMessage.setForeground(Color.RED);

        JButton startNewGame = new JButton("Start New Game");
        JButton getHelp = new JButton("Get Help");
        JButton quit = new JButton("Quit");

        startNewGame.setFont(new Font("Serif", Font.PLAIN, 25));
        getHelp.setFont(new Font("Serif", Font.PLAIN, 25));
        quit.setFont(new Font("Serif", Font.PLAIN, 25));

        startNewGame.addActionListener(e -> {
            // TODO open your game
        });

        getHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help = new JFrame("Help");
                help.setSize(600, 600);
                help.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                JPanel helpPanel = new JPanel();
                JLabel gameHelp = new JLabel();
                gameHelp.setText("<html>First line<br>Second line</html>");
                helpPanel.add(gameHelp);
                help.add(helpPanel);
                help.setVisible(true);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(welcomeMessage);
        mainPanel.add(startNewGame);
        mainPanel.add(getHelp);
        mainPanel.add(quit);


        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);

    }

    public void setWindowVisibility(boolean visibility) {
        mainFrame.setVisible(visibility);
    }

}
