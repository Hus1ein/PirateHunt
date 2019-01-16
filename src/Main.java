import Models.Enemy;
import Models.MatrixField;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static JFrame jf;
    static JPanel jp;
    private static Player player;
    private static List<Enemy> enemies;
    private static ArrayList<MatrixField> barriers;
    private static JButton[][] jb;
    private static MatrixField target;
    private static GameLogic gameLogic;
    public static KeyListener kl;


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        jf = new JFrame("PACMAN");
        jf.setSize(980, 980);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        jp.setLayout(new GridLayout(15, 15));

        jb = new JButton[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                jb[i][j] = new JButton();
                jb[i][j].setSize(65, 65);
                jb[i][j].setEnabled(false);

                jb[i][j].setBackground(Color.BLUE);
                jp.add(jb[i][j]);
            }
        }

        gameLogic = new GameLogic();
        gameLogic.init("Hussain", 1);
        player = gameLogic.getCurrentPlayer();
        barriers = gameLogic.getBarriers();
        Image imageIcon = new ImageIcon(Main.class.getResource("/images/yatch2.png")).getImage();
        jb[player.getPosition().getRow()][player.getPosition().getColumn()].setIcon(new ImageIcon(imageIcon));
        jb[player.getPosition().getRow()][player.getPosition().getColumn()].setBackground(Color.GREEN);

        target = gameLogic.getTarget();
        Image imageIcon1 = new ImageIcon(Main.class.getResource("/images/flag.png")).getImage();
        jb[target.getRow()][target.getColumn()].setIcon(new ImageIcon(imageIcon1));
        jb[target.getRow()][target.getColumn()].setBackground(Color.YELLOW);

        enemies = gameLogic.getEnemies();

        for (int i = 0; i < enemies.size();  i++) {
            jb[enemies.get(i).getPosition().getRow()][enemies.get(i).getPosition().getColumn()].setBackground(Color.RED);
        }

        for (int i = 0; i < barriers.size();  i++) {
            jb[barriers.get(i).getRow()][barriers.get(i).getColumn()].setBackground(new Color(153, 102, 51));
        }

        jf.add(jp);
        jf.setResizable(false);
        jf.setVisible(true);

        kl = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.getKeyCode() == 37) {
                    if (player.getPosition().getColumn() >= 1 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow(), player.getPosition().getColumn() - 1)) {
                        gameLogic.startGame("left");
                        System.out.println("IDEMO LIJEVO");
                    }

                }

                if (e.getKeyCode() == 38) {
                    if (player.getPosition().getRow() >= 1 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow() - 1, player.getPosition().getColumn())) {
                        gameLogic.startGame("up");
                        System.out.println("IDEMO GORE");
                    }
                }

                if (e.getKeyCode() == 39) {
                    if (player.getPosition().getColumn() <= 13 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow(), player.getPosition().getColumn() + 1)) {
                        gameLogic.startGame("right");
                        System.out.println("IDEMO DESNO");
                    }

                }

                if (e.getKeyCode() == 40) {
                    if (player.getPosition().getRow() <= 13 && gameLogic.noBarriersOnTheWay(player.getPosition().getRow() + 1, player.getPosition().getColumn())) {
                        gameLogic.startGame("down");
                        System.out.println("IDEMO DOLJE");
                    }
                }

                if (gameLogic.isPlayerWin()) {
                    System.out.println("Win");
                    gameOver();
                } else if (gameLogic.isPlayerLose()) {
                    System.out.println("Lose");
                    gameOver();
                }else if (!gameLogic.isPlayerWin() && !gameLogic.isPlayerLose() && (e.getKeyCode() == 37 || e.getKeyCode() == 38 || e.getKeyCode() == 39 || e.getKeyCode() == 40)) {
                    for (int i=0; i<15; i++) {
                        for (int j=0; j<15; j++) {
                            if (target.getRow() != i || target.getColumn() != j) {
                                jb[i][j].setBackground(Color.BLUE);
                                jb[i][j].setIcon(null);
                            }

                        }
                    }
                    player = gameLogic.getCurrentPlayer();
                    Image imageIcon = new ImageIcon(Main.class.getResource("/images/yatch2.png")).getImage();
                    jb[player.getPosition().getRow()][player.getPosition().getColumn()].setIcon(new ImageIcon(imageIcon));
                    jb[player.getPosition().getRow()][player.getPosition().getColumn()].setBackground(Color.GREEN);

                    enemies = gameLogic.getEnemies();
                    for (int i = 0; i < enemies.size();  i++) {
                        jb[enemies.get(i).getPosition().getRow()][enemies.get(i).getPosition().getColumn()].setBackground(Color.RED);
                    }

                    barriers = gameLogic.getBarriers();
                    for (int i = 0; i < barriers.size();  i++) {
                        jb[barriers.get(i).getRow()][barriers.get(i).getColumn()].setBackground(new Color(153, 102, 51));
                    }
                }



            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        };
        jf.addKeyListener(kl);



        //jf.addKeyListener(kl);
        /*while (true) {
            if (game.end()) break;

            game.movePacMan();
            game.moveEnemies();

            for (int i=0; i<rowNum; i++) {
                for (int j=0; j<colNum; j++) {
                    int v = game.board.board[i][j];
                    Color c;

                    if (v == game.board.PACMAN) {
                        c = Color.YELLOW;
                    } else if (v == game.board.ENEMY) {
                        c = Color.BLUE;
                    } else if (v == game.board.WALL) {
                        c = Color.GRAY;
                    } else {
                        c = Color.BLACK;
                    }

                    jb[i][j].setBackground(c);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
    }

    private static void gameOver() {
        player = gameLogic.getCurrentPlayer();
        Image imageIcon = new ImageIcon(Main.class.getResource("/images/yatch2.png")).getImage();
        jb[player.getPosition().getRow()][player.getPosition().getColumn()].setIcon(new ImageIcon(imageIcon));
        jb[player.getPosition().getRow()][player.getPosition().getColumn()].setBackground(Color.GREEN);
        jf.removeKeyListener(kl);
    }
}
