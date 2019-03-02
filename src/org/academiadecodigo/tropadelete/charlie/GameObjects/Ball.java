package org.academiadecodigo.tropadelete.charlie.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.tropadelete.charlie.Drawable;
import org.academiadecodigo.tropadelete.charlie.PlayerNumber;

public class Ball implements Drawable {

    private final int SPEED = 10;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private Ellipse ellipse;
    private Direction direction;
    private static int BOUND = 20;

    private boolean colliding;
    private boolean isStatic;
    private PlayerNumber startingPlayer;

    //initialize the ball with a motion
    //ball starts at the center of the screen
    public Ball(int x, int y, PlayerNumber playerNumber) {
        this.x = x;
        this.y = y;

        ellipse = new Ellipse(x, y, 20, 20);
        ellipse.setColor(Color.PINK);
        isStatic = true;
        startingPlayer = playerNumber;

        dx = dy = 0;
    }

    /**
     * Moves this Ball by incrementing the x and y parameters according to the
     * deltaX and deltaY parameters, correspondingly
     */
    public void move() {
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

    public void delete() {ellipse.delete();}

    /**
     * Returns the Ellipse shape of this Ball.
     *
     * @return the Ellipse
     */
    public Ellipse getEllipse() {
        return ellipse;
    }

    /**
     * Sets the proper values of deltaX and deltaY based on the angle parameter received,
     * after converting it to radians.
     *
     * @param angleDegree the angle in degrees
     */
    public void setDeltaByAngle(double angleDegree) {

        double radians = Math.toRadians(angleDegree);

        dx = (int) (SPEED * Math.cos(radians));
        dy = (int) (SPEED * Math.sin(radians));

        updateDirection();
    }

    public void setDeltaByBounce(Direction blockColSide) {

        switch (blockColSide) {
            case UP:
                invertDeltaY();
                break;

            case UPPER_RIGHT:
                colUpperRight();
                break;

            case RIGHT:
                invertDeltaX();
                break;

            case LOWER_RIGHT:
                colLowerRight();
                break;

            case DOWN:
                invertDeltaY();
                break;

            case LOWER_LEFT:
                colLowerLeft();
                break;

            case LEFT:
                invertDeltaX();
                break;

            case UPPER_LEFT:
                colUpperLeft();

        }

        updateDirection();
    }

    public void setDeltaByBouncePlayer() {
        invertDeltaX();
        updateDirection();
    }


    private void colUpperRight() {

        if (direction.equals(Direction.LOWER_RIGHT)) {
            invertDeltaY();
            return;
        }

        if (direction.equals(Direction.UPPER_LEFT)) {
            invertDeltaX();
            return;
        }

        invertDeltaX();
        invertDeltaY();
    }

    private void colLowerRight() {

        if (direction.equals(Direction.LOWER_LEFT)) {
            invertDeltaX();
            return;
        }

        if (direction.equals(Direction.UPPER_RIGHT)) {
            invertDeltaY();
            return;
        }

        invertDeltaX();
        invertDeltaY();
    }

    private void colLowerLeft() {

        if (direction.equals(Direction.LOWER_RIGHT)) {
            invertDeltaX();
            return;
        }

        if (direction.equals(Direction.UPPER_LEFT)) {
            invertDeltaY();
            return;
        }

        invertDeltaX();
        invertDeltaY();
    }

    private void colUpperLeft() {

        if (direction.equals(Direction.LOWER_LEFT)) {
            invertDeltaY();
            return;
        }

        if (direction.equals(Direction.UPPER_RIGHT)) {
            invertDeltaX();
            return;
        }

        invertDeltaX();
        invertDeltaY();
    }


    public void setDeltaByWall() {
        invertDeltaY();
        updateDirection();
    }


    private void invertDeltaX() {
        dx *= -1;
    }

    private void invertDeltaY() {
        dy *= -1;
    }

    private void updateDirection() {

        if (dx == 0 && dy < 0) {
            direction = Direction.UP;
        }

        if (dx > 0 && dy < 0) {
            direction = Direction.UPPER_RIGHT;
        }

        if (dx > 0 && dy == 0) {
            direction = Direction.RIGHT;
        }

        if (dx > 0 && dy > 0) {
            direction = Direction.LOWER_RIGHT;
        }

        if (dx == 0 && dy > 0) {
            direction = Direction.DOWN;
        }

        if (dx < 0 && dy > 0) {
            direction = Direction.LOWER_LEFT;
        }

        if (dx < 0 && dy == 0) {
            direction = Direction.LEFT;
        }

        if (dx < 0 && dy < 0) {
            direction = Direction.UPPER_LEFT;
        }
    }

    public static int getBound() {
        return BOUND;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void removeStatic() {
        isStatic = false;
    }

    public PlayerNumber getStartingPlayer() {
        return startingPlayer;
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
}
