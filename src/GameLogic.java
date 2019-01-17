import Models.Enemy;
import Models.MatrixField;
import Models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    private int level;
    private Player currentPlayer;
    private ArrayList<Enemy> enemies;
    private ArrayList<MatrixField> barriers;
    private MatrixField target;
    private ArrayList<ArrayList<MatrixField>> matrix;
    private boolean gameOver;
    public static final int EASY = 1;
    public static final int AVERAGE = 2;
    public static final int HARD = 3;

    public void init(String playerName, int level) {
        this.gameOver = false;
        this.level = level;
        this.barriers = new ArrayList<>();
        this.matrix = createMatrix();
        this.target = createTarget();
        matrix.get(target.getRow()).get(target.getColumn()).setStatus(MatrixField.TARGET);

        this.currentPlayer = createPlayer(playerName);
        matrix.get(currentPlayer.getPosition().getRow()).get(currentPlayer.getPosition().getColumn()).setStatus(MatrixField.PLAYER);

        this.enemies = new ArrayList<>();
        createEnemies();

        //printMatrix();
    }

    public void changeMatrixFieldsStatus(MatrixField oldField, MatrixField newField) {
        matrix.get(oldField.getRow()).get(oldField.getColumn()).setStatus(oldField.getStatus());
        matrix.get(newField.getRow()).get(newField.getColumn()).setStatus(newField.getStatus());
    }

    private MatrixField createTarget() {
        int targetPosition = (new Random()).nextInt(4);
        MatrixField field;
        if (targetPosition == 0) {
            field = new MatrixField(0, 0);
        } else if(targetPosition == 1) {
            field = new MatrixField(0, 14);
        } else if (targetPosition == 2) {
            field = new MatrixField(14, 0);
        } else {
            field = new MatrixField(14, 14);
        }
        return field;
    }

    private Player createPlayer(String playerName) {
        Player player;
        if (this.target.getRow() == 0 && this.target.getColumn() == 0) {
            player = new Player(playerName, new MatrixField(14, 14));
        } else if (this.target.getRow() == 0 && this.target.getColumn() == 14) {
            player = new Player(playerName, new MatrixField(14, 0));
        } else if (this.target.getRow() == 14 && this.target.getColumn() == 0) {
            player = new Player(playerName, new MatrixField(0, 14));
        } else {
            player = new Player(playerName, new MatrixField(0, 0));
        }

        return player;
    }
    private ArrayList<ArrayList<MatrixField>> createMatrix() {
        MatrixField field;
        ArrayList<MatrixField> row;
        ArrayList<ArrayList<MatrixField>> resultMatrix = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            row = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                field = new MatrixField(i, j, MatrixField.EMPTY);
                row.add(field);
            }
            resultMatrix.add(row);
        }

        return resultMatrix;
    }

    private List<Enemy> createEnemies() {
        int enemiesNumber = 0;
        if (this.level == 1) {
            enemiesNumber = 4;
        } else if (this.level == 2) {
            enemiesNumber = 8;
        } else if (this.level == 3) {
            enemiesNumber = 12;
        }
        List<Enemy> enemiesResult = new ArrayList<>();
        for (int i = 0; i < enemiesNumber / 4; i++) {
            randomEnemyPositions();
        }
        return enemiesResult;
    }

    private void randomEnemyPositions() {
        Random random = new Random();
        MatrixField field;
        int column;
        int row;

        if (this.currentPlayer.getPosition().getRow() == 0 && this.currentPlayer.getPosition().getColumn() == 0) {

            // 1/4 from all enemies
            do {
                row = random.nextInt(4);
                column = random.nextInt(11) + 4;
            } while(matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/4 from all enemies
            do {
                row = random.nextInt(11) + 4;
                column = random.nextInt(4);
            } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/2 from all enemies
            for (int i = 0; i < 2; i++) {
                do {
                    row = random.nextInt(11) + 4;
                    column = random.nextInt(11) + 4;
                } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
                matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
                this.enemies.add(new Enemy(new MatrixField(row, column)));
            }
        } else if (this.currentPlayer.getPosition().getRow() == 0 && this.currentPlayer.getPosition().getColumn() == 14) {

            // 1/4 from all enemies
            do {
                row = random.nextInt(4);
                column = random.nextInt(11);
            } while(matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/4 from all enemies
            do {
                row = random.nextInt(11) + 4;
                column = random.nextInt(4) + 11;
            } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/2 from all enemies
            for (int i = 0; i < 2; i++) {
                do {
                    row = random.nextInt(11) + 4;
                    column = random.nextInt(11);
                } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
                matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
                this.enemies.add(new Enemy(new MatrixField(row, column)));
            }
        } else if (this.currentPlayer.getPosition().getRow() == 14 && this.currentPlayer.getPosition().getColumn() == 0) {

            // 1/4 from all enemies
            do {
                row = random.nextInt(4) + 11;
                column = random.nextInt(11) + 4;
            } while(matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/4 from all enemies
            do {
                row = random.nextInt(11);
                column = random.nextInt(4);
            } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/2 from all enemies
            for (int i = 0; i < 2; i++) {
                do {
                    row = random.nextInt(11);
                    column = random.nextInt(11) + 4;
                } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
                matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
                this.enemies.add(new Enemy(new MatrixField(row, column)));
            }
        } else if (this.currentPlayer.getPosition().getRow() == 14 && this.currentPlayer.getPosition().getColumn() == 14) {

            // 1/4 from all enemies
            do {
                row = random.nextInt(4) + 11;
                column = random.nextInt(11);
            } while(matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/4 from all enemies
            do {
                row = random.nextInt(11);
                column = random.nextInt(4) + 11;
            } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
            matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
            this.enemies.add(new Enemy(new MatrixField(row, column)));

            // 1/2 from all enemies
            for (int i = 0; i < 2; i++) {
                do {
                    row = random.nextInt(11);
                    column = random.nextInt(11);
                } while (matrix.get(row).get(column).getStatus() != MatrixField.EMPTY);
                matrix.get(row).get(column).setStatus(MatrixField.ENEMY);
                this.enemies.add(new Enemy(new MatrixField(row, column)));
            }
        }
    }

    public void play(String direction) {
        //Scanner input = new Scanner(System.in);
        MatrixField oldField = new MatrixField(currentPlayer.getPosition().getRow(), currentPlayer.getPosition().getColumn(), MatrixField.EMPTY);
        switch (direction) {
            case "up":
                currentPlayer.move(Player.UP);
                break;
            case "down":
                currentPlayer.move(Player.DOWN);
                break;
            case "left":
                currentPlayer.move(Player.LEFT);
                break;
            case "right":
                currentPlayer.move(Player.RIGHT);
                break;
        }
        MatrixField newField = new MatrixField(currentPlayer.getPosition().getRow(), currentPlayer.getPosition().getColumn(), MatrixField.PLAYER);
        changeMatrixFieldsStatus(oldField, newField);

            //printMatrix();

    }

    public void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            AI(enemies.get(i));
        }
    }

    public void AI(Enemy enemy) {
        MatrixField oldField = new MatrixField(enemy.getPosition().getRow(), enemy.getPosition().getColumn(), MatrixField.EMPTY);
        int newRow = enemy.getPosition().getRow();
        int newColumn = enemy.getPosition().getColumn();
        if (enemy.getPosition().getColumn() == currentPlayer.getPosition().getColumn()) {
            if (enemy.getPosition().getRow() > currentPlayer.getPosition().getRow()) {
                newRow = enemy.getPosition().getRow() - 1;
            } else {
                newRow = enemy.getPosition().getRow() + 1;
            }

        } else if (enemy.getPosition().getRow() == currentPlayer.getPosition().getRow()) {
            if (enemy.getPosition().getColumn() > currentPlayer.getPosition().getColumn()) {
                newColumn = enemy.getPosition().getColumn() - 1;
            } else {
                newColumn = enemy.getPosition().getColumn() + 1;
            }
        } else {
            if (enemy.getPosition().getColumn() > currentPlayer.getPosition().getColumn() &&
                    enemy.getPosition().getRow() > currentPlayer.getPosition().getRow()) {
                newRow = enemy.getPosition().getRow() - 1;
            } else if (enemy.getPosition().getColumn() > currentPlayer.getPosition().getColumn() &&
                    enemy.getPosition().getRow() < currentPlayer.getPosition().getRow()) {
                newColumn = enemy.getPosition().getColumn() - 1;
            } else if (enemy.getPosition().getColumn() < currentPlayer.getPosition().getColumn() &&
                    enemy.getPosition().getRow() > currentPlayer.getPosition().getRow()) {
                newColumn = enemy.getPosition().getColumn() + 1;
            } else if (enemy.getPosition().getColumn() < currentPlayer.getPosition().getColumn() &&
                    enemy.getPosition().getRow() < currentPlayer.getPosition().getRow()) {
                newRow = enemy.getPosition().getRow() + 1;
            }
        }

        MatrixField newField = new MatrixField(newRow, newColumn, MatrixField.ENEMY);
        if (!noBarriersOnTheWay(newRow, newColumn)) {
            int oldRow = enemy.getPosition().getRow();
            int oldColumn = enemy.getPosition().getColumn();
            if (oldColumn + 1 <= 14 && noBarriersOnTheWay(oldRow, oldColumn + 1)) {
                newColumn = oldColumn + 1;
            } else if (oldColumn - 1 >= 0 && noBarriersOnTheWay(oldRow, oldColumn - 1)) {
                newColumn = oldColumn - 1;
            } else if (oldRow + 1 <= 14 && noBarriersOnTheWay(oldRow + 1, oldColumn)) {
                newRow = oldRow + 1;
            } else if (oldRow - 1 >= 0 && noBarriersOnTheWay(oldRow - 1, oldColumn)) {
                newRow = oldRow - 1;
            }
            newField = new MatrixField(newRow, newColumn, MatrixField.ENEMY);
        }

        //Create barrier
        for (int i = 0; i < enemies.size(); i++) {
            if (newRow == enemies.get(i).getPosition().getRow() && newColumn == enemies.get(i).getPosition().getColumn()) {
                createBarrier(enemy, enemies.get(i), newRow, newColumn);
                return;
            }
        }

        enemy.getPosition().setRow(newRow);
        enemy.getPosition().setColumn(newColumn);

        changeMatrixFieldsStatus(oldField, newField);

    }

    public void printMatrix() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                    if (matrix.get(i).get(j).getStatus() == MatrixField.ENEMY || matrix.get(i).get(j).getStatus() == MatrixField.PLAYER) {
                        System.out.print(matrix.get(i).get(j).getStatus() + "    ");
                    } else {
                        System.out.print(" " + "    ");
                    }


            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public MatrixField getTarget() {
        return target;
    }

    public ArrayList<MatrixField> getBarriers() {
        return barriers;
    }

    public boolean isPlayerLose() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPosition().getRow() == currentPlayer.getPosition().getRow() && enemies.get(i).getPosition().getColumn() == currentPlayer.getPosition().getColumn()) {
                return true;
            }
        }

        return false;
    }
    public boolean isPlayerWin() {
        if (currentPlayer.getPosition().getRow() == target.getRow() && currentPlayer.getPosition().getColumn() == target.getColumn()) {
            return true;
        }

        return false;
    }

    private void createBarrier(Enemy firstEnemy, Enemy secondEnemy, int row, int column) {
        enemies.remove(firstEnemy);
        enemies.remove(secondEnemy);
        barriers.add(new MatrixField(row, column));
        matrix.get(row).get(column).setStatus(MatrixField.BARRIER);
    }

    public boolean noBarriersOnTheWay(int row, int column) {
        for (int i = 0; i < barriers.size(); i++) {
            if (barriers.get(i).getRow() == row && barriers.get(i).getColumn() == column) {
                return false;
            }
        }

        return true;
    }

}
