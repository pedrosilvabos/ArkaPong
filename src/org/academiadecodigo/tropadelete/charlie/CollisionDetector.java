package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Block;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Direction;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;
import org.academiadecodigo.tropadelete.charlie.PlayerNumber;

public class CollisionDetector {

    /**
     * Checks if this Ball collides with this Player
     *
     * @param ball the Shape of the Ball, for measurements' reference
     * @param player the Player, to be able to reference its measurements
     * @return true if there is a collision between the two; false otherwise
     */
    public static int ballCollidesWithPlayer(Ball ball, Player player,int touchCount) {

        Ellipse eBall = ball.getEllipse();
        Rectangle rPlayer = player.getRectangle();

        if (player.getPlayerNumber() == PlayerNumber.ONE) {

            if (p1CheckX(eBall, rPlayer) && checkY(eBall, rPlayer)) {
                System.out.println("BallY: " + eBall.getY() + " PadY: " + rPlayer.getY());
                ball.setDeltaByBouncePlayer();
                touchCount++;
            }

        }

        if (player.getPlayerNumber() == PlayerNumber.TWO) {

            if (p2CheckX(eBall, rPlayer) && checkY(eBall, rPlayer)) {
                System.out.println("BallY: " + eBall.getY() + " PadY: " + rPlayer.getY());
                ball.setDeltaByBouncePlayer();
                touchCount++;
            }
        }
        return touchCount;
    }


    private static boolean p1CheckX(Ellipse eBall, Rectangle rPlayer) {

        return eBall.getX() <= rPlayer.getX() + rPlayer.getWidth();
    }

    private static boolean p2CheckX(Ellipse eBall, Rectangle rPlayer) {

        return eBall.getX() + eBall.getWidth() >= rPlayer.getX();
    }

    private static boolean checkY(Ellipse eBall, Rectangle rPlayer) {

        return eBall.getY() + eBall.getHeight() >= rPlayer.getY() && eBall.getY() <= rPlayer.getY() + rPlayer.getHeight();
    }

    /**
     * Checks if this Ball collides with the upper or lower wall of the Stage
     *
     * @param ball
     * @param stage the Rectangle of the Stage, for measurements' reference
     */
    public static void ballCollidesWithWalls(Ball ball, Rectangle stage) {

        Shape bShape = ball.getEllipse();

        if (bShape.getY() <= stage.getY() || (bShape.getY() + bShape.getHeight()) >= (stage.getY() + stage.getHeight())) {
            ball.setDeltaByWall();
        }
    }

    public static boolean ballCollidesWithBlocks(Ball ball, Block block) {

        Shape ballS = ball.getEllipse();

        /** Check upperLeft point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY())) {

            /** Check upperRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY())) {
                ball.setDeltaByBounce(Direction.DOWN);
                return true;
            }

            /** Check lowerLeft point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY() + ballS.getHeight())) {
                ball.setDeltaByBounce(Direction.RIGHT);
                return true;
            }

            ball.setDeltaByBounce(Direction.LOWER_RIGHT);
            return true;
        }

        /** Check upperRight point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY())) {

            /** Check lowerRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                ball.setDeltaByBounce(Direction.LEFT);
                return true;
            }

            ball.setDeltaByBounce(Direction.LOWER_LEFT);
            return true;
        }

        /** Check lowerLeft point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY() + ballS.getHeight())) {

            /** Check lowerRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                ball.setDeltaByBounce(Direction.UP);
                return true;
            }

            ball.setDeltaByBounce(Direction.UPPER_RIGHT);
            return true;
        }

        /** Check lowerRight point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
            ball.setDeltaByBounce(Direction.UPPER_LEFT);
            return true;
        }

        return false;
    }

    public static boolean pointWithinBlock(Rectangle block, int x, int y) {
        return ( (x <= block.getX() + block.getWidth() && x >= block.getX()) &&
                (y <= block.getY() + block.getHeight() && y >= block.getY()) );
    }


    public static PlayerNumber ballCollisionGoal(Ellipse ball, Rectangle stage) {

        PlayerNumber pn = PlayerNumber.NONE;
        int spacing = 10;

        if (ball.getX() <= stage.getX() + spacing) {
            pn = PlayerNumber.ONE;
        }

        if (ball.getX() + ball.getWidth() >= stage.getX() + stage.getWidth() - spacing) {
            pn = PlayerNumber.TWO;
        }

        return pn;
    }
}
