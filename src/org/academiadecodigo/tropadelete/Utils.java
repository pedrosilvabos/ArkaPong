package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

import java.util.Random;

public class Utils {


    /**
     * initializes a new Ball in the center of the Stage
     * and throws it to a random direction of left and right
     *
     * @return the ball centered in Stage and with correct delta values
     */
    public static Ball startBall(Rectangle canvas) {

        // set ball to center
        Ball ball = new Ball(canvas.getX() + (canvas.getWidth() / 2), canvas.getY() + (canvas.getHeight() / 2));

        // wait for 1 to 2 seconds

        Random random = new Random();
        boolean isRight = random.nextBoolean();

        if (isRight) {
            ball.setDelta(0);
        } else {
            ball.setDelta(180);
        }
        return ball;
    }

    public static void ballCollisionWithPaddle(Ball b, Player p) {

        Shape paddle = (Shape) p.getRectangle();
        Shape ball = (Shape) b.getEllipse();


    }


}