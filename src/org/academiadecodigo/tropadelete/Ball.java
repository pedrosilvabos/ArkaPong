package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Ball implements Drawable {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int SPEED = 10;
    private Ellipse ellipse;
    
    //initialize the ball with a motion
    //ball starts at the center of the screen
    public Ball(int x, int y){
        this.x = x;
        this.y = y;

        ellipse = new Ellipse(x, y, 20, 20);
        ellipse.setColor(Color.PINK);

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

    /**
     * Paints the ellipse on the screenWriter
     *
     * @see Ellipse#draw()
     */
    public void draw() {
        ellipse.fill();
    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    /**
     * Sets the value of the property deltaX.
     *
     * @param angleDegree
     */
    public void setDelta(double angleDegree) {

        int radians = (int) Math.toRadians(angleDegree);

        dx = SPEED * (int) Math.round(Math.cos(radians));
        dy = SPEED * (int) Math.round(Math.sin(radians));
    }

    public void bounce(AxisBounce axis) {

        switch (axis) {
            case X:
                dx *= -1;
                break;

            case Y:
                dy *= -1;
                break;

            default:
                dx *= -1;
                dy *= -1;
        }
    }

}
