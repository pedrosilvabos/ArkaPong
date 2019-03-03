package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;

import java.util.Random;

public class Utils {


    /**
     * initializes a new Ball in the center of the Stage
     * and throws it to a random direction of left and right
     *
     * @return the ball centered in Stage and with correct delta values
     */
    public static Ball startBall(Player player1, Player player2) {

        int ballPaddleDistance = 50;
        boolean isFacingPlayerTwo = new Random().nextBoolean();
        boolean startAngleUp = new Random().nextBoolean();
        int startAngle;

        Rectangle rPlayer1 = player1.getRectangle();
        Rectangle rPlayer2 = player2.getRectangle();

        Ball ball;

        int startY = rPlayer1.getY() + (rPlayer1.getHeight() / 2) - (Ball.getBound() / 2);

        if (isFacingPlayerTwo) {

            int startX = rPlayer2.getX() - ballPaddleDistance - Ball.getBound();
            startAngle = startAngleUp ? 180 + 30 : 180 - 30;

            ball = new Ball(startX, startY, PlayerNumber.TWO);
        }

        else {

            int startX = rPlayer1.getX() + rPlayer1.getWidth() + ballPaddleDistance;
            startAngle = startAngleUp ? 30 : -30;

            ball = new Ball(startX, startY, PlayerNumber.ONE);
        }

        ball.setDeltaByAngle(startAngle);
        return ball;
    }

    public static boolean checkVictoryCondition(Ball ball, Rectangle canvas, Player player1, Player player2) {

        PlayerNumber playerNumber = CollisionDetector.ballCollisionGoal(ball.getEllipse(), canvas);

        if (playerNumber.equals(PlayerNumber.ONE)) {
            player1.losePoint();
            return true;
        }

        if (playerNumber.equals(PlayerNumber.TWO)) {
            player2.losePoint();
            return true;
        }

        return false;
    }

}