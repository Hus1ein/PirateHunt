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

        welcomeWindow = new WelcomeWindow();
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
        });

    }
}
