package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

public class CollisionDetector {

    /**
     * Checks if this Ball collides with this Player
     *
     * @param ball the Shape of the Ball, for measurements' reference
     * @param player the Player, to be able to reference its measurements
     * @return true if there is a collision between the two; false otherwise
     */
    public static boolean ballCollidesWithPlayer(Shape ball, Player player) {

        Rectangle pRect = player.getRectangle();

        if (player.getPlayerNumber() == PlayerNumber.ONE) {
            return p1CheckX(ball, pRect) && checkY(ball, pRect);
        } else {
            return p2CheckX(ball, pRect) && checkY(ball, pRect);
        }
    }

    /**
     * Checks if this Ball's X coordinate is between two acceptable values of Player's Shape
     *
     * @param ball the Shape of this Ball, to get its X coordinate
     * @param paddle the Shape of this Player's Rectangle, to get its X coordinate
     * @return
     */
    private static boolean p1CheckX(Shape ball, Shape paddle) {
        return ball.getX() <= paddle.getX() + paddle.getWidth() && ball.getX() >= paddle.getX() + (paddle.getWidth() / 2);
    }

    private static boolean p2CheckX(Shape ball, Shape paddle) {
        return ball.getX() <= paddle.getX() + (paddle.getWidth() / 2) && ball.getX() >= paddle.getX();
    }

    private static boolean checkY(Shape ball, Shape paddle) {
        return ball.getY() <= paddle.getY() && ball.getY() >= paddle.getY() + (paddle.getHeight() / 2);
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
            System.out.println("here");
            ball.bounce(AxisBounce.Y);
        }
    }

    public static void ballCollidesWithBlocks(Ball ball, Block[] blocks) {

        Shape ballS = ball.getEllipse();

        for (Block block : blocks) {    // TODO: make a switch statement
            // check if block is active

            /** Check upperLeft point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY())) {
                System.out.println("UpperLeft");

                /** Check upperRight point of Ball */
                if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY())) {
                    ball.bounce(AxisBounce.Y);
                    System.out.println("UpperRight");
                    return;
                }

                /** Check lowerLeft point of Ball */
                if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY() + ballS.getHeight())) {
                    ball.bounce(AxisBounce.X);
                    System.out.println("LowerLeft");
                    return;
                }

                ball.bounce(AxisBounce.XY);
                return;
            }

            /** Check upperRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY())) {
                System.out.println("UpperRight");

                /** Check lowerRight point of Ball */
                if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                    ball.bounce(AxisBounce.X);
                    System.out.println("LowerRight");
                    return;
                }

                ball.bounce(AxisBounce.XY);
                return;
            }

            /** Check lowerLeft point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX(), ballS.getY() + ballS.getHeight())) {
                System.out.println("LowerLeft");

                /** Check lowerRight point of Ball */
                if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                    ball.bounce(AxisBounce.Y);
                    System.out.println("LowerRight ");
                    return;
                }

                ball.bounce(AxisBounce.XY);
                return;
            }

            /** Check lowerRight point of Ball */
            if (pointWithinBlock(block.getRectangle(), ballS.getX() + ballS.getWidth(), ballS.getY() + ballS.getHeight())) {
                ball.bounce(AxisBounce.XY);
                System.out.println("LowerRight");
            }
        }
    }

    public static boolean pointWithinBlock(Rectangle block, int x, int y) {
        return ( (x <= block.getX() + block.getWidth() && x >= block.getX()) &&
                 (y <= block.getY() + block.getHeight() && y >= block.getY()) );
    }


    /**
     * Probably won't need these anymore , or perhaps will need to change them
     */
    private static int upperLeftVert(Shape shape) {
        return shape.getX();
    }

    private static int upperRightVert(Shape shape) {
        return shape.getX() + shape.getWidth();
    }

    private static int lowerLeftVert(Shape shape) {
        return shape.getY() + shape.getHeight();
    }

    private static int lowerRightVert(Shape shape) {
        return shape.getX() + shape.getWidth() + shape.getHeight();
    }

}
