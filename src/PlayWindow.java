import Models.Enemy;
import Models.MatrixField;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayWindow implements KeyListener {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton[][] buttons;
    private String playerName;
    private int level;
    private GameLogic gameLogic;
    private Player player;
    private ArrayList<MatrixField> barriers;
    private ArrayList<Enemy> enemies;
    private MatrixField target;


    public PlayWindow(String playerName, int level) {
        this.playerName = playerName;
        this.level = level;
    }


    public void startWindow() {

        mainFrame = new JFrame("Pirate Hunt");
        mainFrame.setSize(980, 980);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(15, 15));

        buttons = new JButton[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(65, 65);
                buttons[i][j].setEnabled(false);
                buttons[i][j].setBackground(Color.BLUE);

                mainPanel.add(buttons[i][j]);
            }
        }

        gameLogic = new GameLogic();
        gameLogic.init(playerName, level);
        player = gameLogic.getCurrentPlayer();
        barriers = gameLogic.getBarriers();
        enemies = gameLogic.getEnemies();
        target = gameLogic.getTarget();

        setElementsOfGame();


        mainFrame.add(mainPanel);
        mainFrame.addKeyListener(this);
        mainFrame.setResizable(false);

    }

    private void setElementsOfGame() {

        Image playerIcon = new ImageIcon(Main.class.getResource("/images/yatch2.png")).getImage();
        buttons[player.getPosition().getRow()][player.getPosition().getColumn()].setIcon(new ImageIcon(playerIcon));
        buttons[player.getPosition().getRow()][player.getPosition().getColumn()].setBackground(Color.GREEN);


        Image targetIcon = new ImageIcon(Main.class.getResource("/images/flag.png")).getImage();
        buttons[target.getRow()][target.getColumn()].setIcon(new ImageIcon(targetIcon));
        buttons[target.getRow()][target.getColumn()].setBackground(Color.YELLOW);


        for (int i = 0; i < enemies.size();  i++) {
            buttons[enemies.get(i).getPosition().getRow()][enemies.get(i).getPosition().getColumn()].setBackground(Color.RED);
        }

        for (int i = 0; i < barriers.size();  i++) {
            buttons[barriers.get(i).getRow()][barriers.get(i).getColumn()].setBackground(new Color(153, 102, 51));
        }
    }

    private void clearElementsOfGame() {
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                if (target.getRow() != i || target.getColumn() != j) {
                    buttons[i][j].setBackground(Color.BLUE);
                    buttons[i][j].setIcon(null);
                }

            }
        }
    }

    public void setWindowVisibility(boolean visibility) {
        mainFrame.setVisible(visibility);
    }

    private void gameOver() {
        clearElementsOfGame();

        player = gameLogic.getCurrentPlayer();
        Image imageIcon = new ImageIcon(Main.class.getResource("/images/yatch2.png")).getImage();
        buttons[player.getPosition().getRow()][player.getPosition().getColumn()].setIcon(new ImageIcon(imageIcon));
        buttons[player.getPosition().getRow()][player.getPosition().getColumn()].setBackground(Color.GREEN);
        mainFrame.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("To sam");
        if (e.getKeyCode() == 37) {
            if (player.getPosition().getColumn() >= 1 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow(), player.getPosition().getColumn() - 1)) {
                gameLogic.play("left");
            }

        }

        if (e.getKeyCode() == 38) {
            if (player.getPosition().getRow() >= 1 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow() - 1, player.getPosition().getColumn())) {
                gameLogic.play("up");
            }
        }

        if (e.getKeyCode() == 39) {
            if (player.getPosition().getColumn() <= 13 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow(), player.getPosition().getColumn() + 1)) {
                gameLogic.play("right");
            }

        }

        if (e.getKeyCode() == 40) {
            if (player.getPosition().getRow() <= 13 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow() + 1, player.getPosition().getColumn())) {
                gameLogic.play("down");
            }
        }

        if (gameLogic.isPlayerWin()) {
            System.out.println("Win");
            gameOver();
        } else if (gameLogic.isPlayerLose()) {
            System.out.println("Lose");
            gameOver();
        }else if (!gameLogic.isPlayerWin() && !gameLogic.isPlayerLose() && (e.getKeyCode() == 37 || e.getKeyCode() == 38 || e.getKeyCode() == 39 || e.getKeyCode() == 40)) {
            player = gameLogic.getCurrentPlayer();
            enemies = gameLogic.getEnemies();
            barriers = gameLogic.getBarriers();

            clearElementsOfGame();
            setElementsOfGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
