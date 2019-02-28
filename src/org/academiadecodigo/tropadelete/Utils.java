package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Utils {


    /**
     * initializes a new org.academiadecodigo.tropadelete.Ball in the center of the org.academiadecodigo.tropadelete.Stage
     * and throws it to a random direction of left and right
     */
    public static Ball startBall(Rectangle canvas) {


        // set ball to center
        Ball ball = new Ball(canvas.getX() + (canvas.getWidth() / 2), canvas.getY() + (canvas.getHeight() / 2));

        // wait for 1 to 2 seconds

        int rand = (int) Math.floor(Math.random() * 2);

        if (rand == 1) {
            ball.setDelta(0);       // RIGHT
        } else {
            ball.setDelta(180);     // LEFT
        }
        return ball;
    }
}