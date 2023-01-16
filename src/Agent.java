import java.awt.*;

public class Agent {
    private final double SPEED = 5;
    private double x, y, prev_x, prev_y;
    private Dimension scope;
    private Vector2D vector;

    public Agent(Dimension scope) {
        this.vector = new Vector2D(Math.random() * 360, this.SPEED);
        this.x = scope.width * Math.random();
        this.y = scope.height * Math.random();
        this.scope = scope;
    }

    private void outOfBounds(double x, double y) {
        this.x = (scope.width  + x) % scope.width;
        this.y = (scope.height + y) % scope.height;
        prev_x = this.x;
        prev_y = this.y;
    }

    public void move() {
        double new_x = x + vector.getX(), new_y = y + vector.getY();

        if (0 <= new_x && new_x < scope.width && 0 <= new_y && new_y < scope.height) {
            prev_x = x;
            prev_y = y;
            x = new_x;
            y = new_y;
        } else {
            outOfBounds(new_x, new_y);
        }
    }

    public void rotate(double alpha) {
        this.vector = this.vector.rotate(alpha);
    }

    public int getX() {return (int) x;}

    public int getY() {return (int) y;}

    public int getPrev_x() {return (int) prev_x;}

    public int getPrev_y() {return (int) prev_y;}

    public Vector2D getVector() {return this.vector;}
}
