package Models;

public class Player {

    private String name;
    private int score;
    private MatrixField position;
    public static final int UP = 0;
    public static final int UP_RIGHT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN_RIGHT = 3;
    public static final int DOWN = 4;
    public static final int DOWN_LEFT = 5;
    public static final int LEFT = 6;
    public static final int UP_LEFT = 7;


    public Player(String name, MatrixField position) {
        this.name = name;
        this.position = position;
        this.score = 0;
    }

    public void move(int direction) {

        switch (direction) {
            case UP:
                position.setRow(position.getRow() - 1);
                break;
            /*case UP_RIGHT:
                row--;
                column++;
                break;*/
            case RIGHT:
                position.setColumn(position.getColumn() + 1);
                break;
            /*case DOWN_RIGHT:
                row++;
                column++;
                break;*/
            case DOWN:
                position.setRow(position.getRow() + 1);
                break;
            /*case DOWN_LEFT:
                row++;
                column--;
                break;*/
            case LEFT:
                position.setColumn(position.getColumn() - 1);
                break;
            /*case UP_LEFT:
                row--;
                column--;
                break;
                */
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public MatrixField getPosition() {
        return position;
    }

    public void setPosition(MatrixField position) {
        this.position = position;
    }
}