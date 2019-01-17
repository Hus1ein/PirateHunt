import Models.Enemy;
import Models.MatrixField;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Main{

    private static WelcomeWindow welcomeWindow;

    public static void main(String[] args) {

        final JComponent[] inputs = new JComponent[] {
                new JLabel("You are Win"),
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Pirate Hunt", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {

        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
        }

        /*welcomeWindow = new WelcomeWindow();
        welcomeWindow.startWindow();
        welcomeWindow.setWindowVisibility(true);
        welcomeWindow.setHelperInterface(new WelcomeWindow.HelperInterface() {
            @Override
            public void openPlayWindow(String playerName, int level) {
                welcomeWindow.setWindowVisibility(false);
                PlayWindow playWindow = new PlayWindow(playerName, level);
                playWindow.startWindow();
                playWindow.setWindowVisibility(true);
            }
        });*/

        /*Test test = new Test();
        test.startWindow();
        test.setWindowVisibility(true);*/

    }
}
