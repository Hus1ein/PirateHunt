import Models.Enemy;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Main {

    static JFrame jf;
    static JPanel jp;
    private static Player player;
    private static List<Enemy> enemies;
    private static JButton[][] jb;


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        jf = new JFrame("PACMAN");
        jf.setSize(980, 980);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        jp.setLayout(new GridLayout(15, 15));
        jp.setBackground(Color.BLUE);

        jb = new JButton[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                jb[i][j] = new JButton();
                jb[i][j].setSize(65, 65);
                jb[i][j].setEnabled(false);

                /*int v = game.board.board[i][j];
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

                jb[i][j].setBackground(c);*/
                jp.add(jb[i][j]);
            }
        }

        GameLogic gameLogic = new GameLogic();
        gameLogic.init("Hussain", 1);
        player = gameLogic.getCurrentPlayer();
        Image imageIcon = new ImageIcon(Main.class.getResource("/images/yatch2.png")).getImage();
        jb[player.getPosition().getRow()][player.getPosition().getColumn()].setIcon(new ImageIcon(imageIcon));

        enemies = gameLogic.getEnemies();

        for (int i = 0; i < enemies.size();  i++) {
            jb[enemies.get(i).getPosition().getRow()][enemies.get(i).getPosition().getColumn()].setBackground(Color.GRAY);
        }

        jf.add(jp);
        jf.setResizable(false);
        jf.setVisible(true);

        KeyListener kl = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.getKeyCode() == 37) {
                    gameLogic.startGame("left");
                    System.out.println("IDEMO LIJEVO");
                }

                if (e.getKeyCode() == 38) {
                    gameLogic.startGame("up");
                    System.out.println("IDEMO GORE");
                }

                if (e.getKeyCode() == 39) {
                    gameLogic.startGame("right");
                    System.out.println("IDEMO DESNO");
                }

                if (e.getKeyCode() == 40) {
                    gameLogic.startGame("down");
                    System.out.println("IDEMO DOLJE");
                }

                for (int i=0; i<15; i++) {
                    for (int j=0; j<15; j++) {
                        jb[i][j].setBackground(Color.white);
                    }
                }
                player = gameLogic.getCurrentPlayer();
                jb[player.getPosition().getRow()][player.getPosition().getColumn()].setBackground(Color.YELLOW);

                enemies = gameLogic.getEnemies();

                for (int i = 0; i < enemies.size();  i++) {
                    jb[enemies.get(i).getPosition().getRow()][enemies.get(i).getPosition().getColumn()].setBackground(Color.GRAY);
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
}
