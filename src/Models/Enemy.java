package Models;

public class Enemy {

    private MatrixField position;

    public Enemy(MatrixField position) {
        this.position = position;
    }

    public MatrixField getPosition() {
        return position;
    }

    public void setPosition(MatrixField position) {
        this.position = position;
    }
}
