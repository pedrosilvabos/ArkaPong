import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Ball implements Drawable {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int SPEED = 2;
    private Ellipse ellipse;
    private boolean exists;
    
    //initialize the ball with a motion
    //ball starts at the center of the screen
    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        ellipse = new Ellipse(x, y, 20, 20);
        ellipse.setColor(Color.PINK);
        exists = true;

        dx = dy = 0;
    }

    /**
     * Moves this Ball by incrementing the x and y parameters according to the
     * deltaX and deltaY parameters, correspondingly
     */
    public void move(){
        x += dx;
        y += dy;
        ellipse.translate(dx, dy);
    }

    public void draw() {
        ellipse.fill();
    }

    /**
     * Sets the value of the property deltaX.
     *
     * @param angle
     */
    public void setDelta(double angle) {
        dx = SPEED * (int) Math.cos(angle);
        dy = SPEED * (int) Math.sin(angle);
    }

    public boolean exists() {
        return exists;
    }
}
