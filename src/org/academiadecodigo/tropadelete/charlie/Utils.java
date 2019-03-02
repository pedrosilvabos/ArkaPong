package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;

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
            ball.setDeltaByAngle(30);
        } else {
            ball.setDeltaByAngle(180 - 30);
        }

        System.out.println("X: " + Math.cos(Math.toRadians(150)));

        System.out.println("isRight: " + isRight);
        return ball;
    }


}