package org.academeiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Utils {


    /**
     * initializes a new org.academeiadecodigo.tropadelete.charlie.Ball in the center of the org.academeiadecodigo.tropadelete.charlie.Stage
     * and throws it to a random direction of left and right
     */
    public static Ball startBall(Rectangle canvas) {

        Ball ball = new Ball(canvas.getX() + (canvas.getWidth() / 2), canvas.getY() + (canvas.getHeight() / 2));

        // set ball to center
        ball = new Ball(canvas.getX() + (canvas.getWidth() / 2), canvas.getY() + (canvas.getHeight() / 2));

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