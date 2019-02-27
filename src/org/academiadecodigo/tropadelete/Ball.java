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
     * Moves this org.academiadecodigo.tropadelete.Ball by incrementing the x and y parameters according to the
     * deltaX and deltaY parameters, correspondingly
     */
    public void move(){
        x += dx;
        y += dy;
        ellipse.translate(dx, dy);
        //System.out.println("X: " + x + " Y: " + y);
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
        //System.out.println("DeltaX: " + dx + " DeltaY: " + dy);
    }

    public void setDirection(int angleRad) {
        dx = SPEED * angleRad;
    }

}
