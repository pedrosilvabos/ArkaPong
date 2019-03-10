package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Block;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Direction;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;

public class CollisionDetector {

    /**
     * Checks if this Ball collides with this Player.
     * Executes the necessary method calls for the collisions.
     *
     * @param ball the Ball reference for collision
     * @param player the Player reference for collision
     * @param touchCount the number of times the Ball has collided with Player
     * @param paddleHit the Sound to be played if Ball collides with Player
     * @return touchCount incremented if there is collision; same value otherwise
     */
    public static int ballCollidesWithPlayer(Ball ball, Player player,int touchCount, Sound paddleHit) {

        Ellipse eBall = ball.getEllipse();
        Rectangle rPlayer = player.getRectangle();

        if (player.getPlayerNumber() == PlayerNumber.ONE) {

            if (p1CheckX(eBall, rPlayer) && checkY(eBall, rPlayer)) {
                if (!ball.isColliding()) {

                    //hit paddle from player 1
                    paddleHit.play(true);
                    ball.setDeltaByBouncePlayer();
                    ball.setColliding(true);
                    return ++touchCount;
                }

                return touchCount;
            }
            ball.setColliding(false);
        }

        if (player.getPlayerNumber() == PlayerNumber.TWO) {

            if (p2CheckX(eBall, rPlayer) && checkY(eBall, rPlayer)) {
                if (!ball.isColliding()) {

                    //hit paddle from player 2
                    paddleHit.play(true);
                    ball.setDeltaByBouncePlayer();
                    ball.setColliding(true);
                    return ++touchCount;
                }

                return touchCount;
            }
            ball.setColliding(false);
        }
        return touchCount;
    }

    /**
     * Checks if this Ellipse is contained inside the collision area for
     * Player 1's Rectangle's X values
     *
     * @param eBall the Ellipse of the Ball
     * @param rPlayer the Rectangle of the Player
     * @return true if eBall is in collision area; false otherwise
     */
    private static boolean p1CheckX(Ellipse eBall, Rectangle rPlayer) {

        return eBall.getX() <= rPlayer.getX() + rPlayer.getWidth();
    }

    /**
     * Checks if this Ellipse is contained inside the collision area for
     * Player 2's Rectangle's X values
     *
     * @param eBall the Ellipse of the Ball
     * @param rPlayer the Rectangle of the Player
     * @return true if eBall is in collision area; false otherwise
     */
    private static boolean p2CheckX(Ellipse eBall, Rectangle rPlayer) {

        return eBall.getX() + eBall.getWidth() >= rPlayer.getX();
    }

    /**
     * Checks if this Ellipse is contained inside the collision area for
     * the Player's Rectangle's Y values
     *
     * @param eBall the Ellipse of the Ball
     * @param rPlayer the Rectangle of the Player
     * @return true if eBall is in collision area; false otherwise
     */
    private static boolean checkY(Ellipse eBall, Rectangle rPlayer) {

        return eBall.getY() + eBall.getHeight() >= rPlayer.getY() && eBall.getY() <= rPlayer.getY() + rPlayer.getHeight();
    }

    /**
     * Checks if this Ball collides with the upper or lower wall of the Stage
     *
     * @param ball the Ball reference for collision
     * @param stage the Rectangle of the Stage, for measurements' reference
     */
    public static void ballCollidesWithWalls(Ball ball, Rectangle stage) {

        Shape bShape = ball.getEllipse();

        if (bShape.getY() <= stage.getY() || (bShape.getY() + bShape.getHeight()) >= (stage.getY() + stage.getHeight())) {

            if (!ball.isColliding()) {
                ball.setColliding(true);
                ball.setDeltaByWall();
                return;
            }
        }

        ball.setColliding(false);
    }

    /**
     * Checks if this Ball collides with this Block
     *
     * @param ball the Ball reference for collision
     * @param block the Block reference for collision
     * @return true if there is collision; false otherwise
     */
    public static boolean ballCollidesWithBlocks(Ball ball, Block block) {

        Shape ballS = ball.getEllipse();

        /** Check upperLeft point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY())) {

            /** Check upperRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY())) {
                ball.setDeltaByBounce(Direction.DOWN);
                block.hideBlock();
                return true;
            }

            /** Check lowerLeft point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY() + ballS.getHeight())) {
                ball.setDeltaByBounce(Direction.RIGHT);
                block.hideBlock();
                return true;
            }

            ball.setDeltaByBounce(Direction.LOWER_RIGHT);
            block.hideBlock();
            return true;
        }

        /** Check upperRight point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY())) {

            /** Check lowerRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                ball.setDeltaByBounce(Direction.LEFT);
                block.hideBlock();
                return true;
            }

            ball.setDeltaByBounce(Direction.LOWER_LEFT);
            block.hideBlock();
            return true;
        }

        /** Check lowerLeft point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY() + ballS.getHeight())) {

            /** Check lowerRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                ball.setDeltaByBounce(Direction.UP);
                block.hideBlock();
                return true;
            }

            ball.setDeltaByBounce(Direction.UPPER_RIGHT);
            block.hideBlock();
            return true;
        }

        /** Check lowerRight point of Ball */
        if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
            ball.setDeltaByBounce(Direction.UPPER_LEFT);
            block.hideBlock();
            return true;
        }

        return false;
    }

    /**
     * Checks if the specified point of x and y integers are contained
     * inside this Rectangle
     *
     * @param block the Rectangle reference for collision
     * @param x the specified X coordinate
     * @param y the specified Y coordinate
     * @return true is the point is contained in the Rectangle; false otherwise
     */
    public static boolean pointWithinBlock(Rectangle block, int x, int y) {
        return ( (x <= block.getX() + block.getWidth() && x >= block.getX()) &&
                (y <= block.getY() + block.getHeight() && y >= block.getY()) );
    }

    /**
     * Checks if this Ball collides with either area of goal
     *
     * @param ball the Ellipse of the Ball
     * @param stage the Rectangle of the Stage
     * @return the PlayerNumber of the Player whose goal was hit
     */
    public static PlayerNumber ballCollisionGoal(Ellipse ball, Rectangle stage) {

        PlayerNumber pn = PlayerNumber.NONE;
        int spacing = 20;

        if (ball.getX() <= stage.getX() + spacing) {
            pn = PlayerNumber.ONE;
        }

        if (ball.getX() + ball.getWidth() >= stage.getX() + stage.getWidth() - spacing) {
            pn = PlayerNumber.TWO;
        }

        return pn;
    }
}
