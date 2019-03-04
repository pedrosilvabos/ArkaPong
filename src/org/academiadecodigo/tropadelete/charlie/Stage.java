package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Block;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;


public class Stage {

    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 768;
    private final int STAGE_WIDTH = 1680;

    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGTH = (STAGE_HEIGHT / 8) - 1;

    private final int PADDLE_WALL_OFFSET = 30;
    private final int PLAYER1_OFFSET = PADDING     + PADDLE_WALL_OFFSET;
    private final int PLAYER2_OFFSET = STAGE_WIDTH - PADDLE_WALL_OFFSET - 25;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, STAGE_WIDTH, STAGE_HEIGHT);

    private       int ballSpeed      = 10; // initial ball speed
    private final int touchIncrement =  5; // # of touches in the paddle before increment of ball speed (0 = no increment)
    private final int increment      =  1; // increment to the ball speed

    private final String RESOURCES ="resources/themes/";

    private Block[] blocks;
    private Player  player1;
    private Player  player2;
    private static  Ball ball;

    private String backgroundSkin;
    private String paddleLeftSkin;
    private String paddleRightSkin;
    private String ballSkin;
    private String blockSkin;
    private int    touchCount;

    private PlayerNumber roundWinner;
    private PlayerNumber winner;
    private ScreenWriter screenWriter;


    /**
     * Create and skin the stage
     * <p>
     * Randomly choose a combination of skins for the background, paddle ball and blocks
     */
    public Stage() {
        int theme  = (int) (Math.random() * 3);
        int option = 0; // (int) (Math.random() * 3);  // no options for first version

        this.backgroundSkin  = RESOURCES+theme+"/images/background" +option+".png";
        this.paddleLeftSkin  = RESOURCES+theme+"/images/paddleLeft" +option+".png";
        this.paddleRightSkin = RESOURCES+theme+"/images/paddleRight"+option+".png";
        this.ballSkin        = RESOURCES+theme+"/images/ball"       +option+".png";
        this.blockSkin       = RESOURCES+theme+"/images/block"      +option+".png";

        Picture background = new Picture(PADDING, PADDING, this.backgroundSkin);
        background.draw();
        this.touchCount = 0;
    }

    /**
     * Initiate the Game
     * <p>
     * Draw the CANVAS, create the players on each side of the screen
     */
    public void init() {

        CANVAS.draw();
        player1 = new Player(PLAYER1_OFFSET, PADDING, STAGE_HEIGHT, PlayerNumber.ONE,paddleLeftSkin); //LEFT
        player2 = new Player(PLAYER2_OFFSET, PADDING, STAGE_HEIGHT, PlayerNumber.TWO,paddleRightSkin); //RIGHT
        new KeyboardListener(player1, player2);
        makeBlocks(15, 8);

        roundWinner = PlayerNumber.NONE;
        winner = PlayerNumber.NONE;
        screenWriter = new ScreenWriter(player1, player2, CANVAS);
        screenWriter.setPlayerScore();
        screenWriter.setStartBall();
    }

    /**
     * Block factory
     * <p>
     * Generates blocks in rows and columns
     *
     * @params blockCols
     * @params blockRows
     */
    public void makeBlocks(int blockCols, int blockRows) {

        blocks = blockMatrix(BLOCK_WIDTH, BLOCK_HEIGTH, blockCols, blockRows, PADDING, STAGE_WIDTH);
        chooseBlocks(3);
        showBlocks();
    }

    /**
     * Start the game
     * <p>
     * Initiate the thread of the game
     */
    public void start() {

        while (true) {

            /** Game loop stopping condition */

            if (!winner.equals(PlayerNumber.NONE)) {

                // show Player Winner on top of screen
                screenWriter.showWinner();

                continue;
            }


            try {
                Thread.sleep(20);

                /** PLAYERS */

                screenWriter.drawPlayerScores();
                player1.move();
                player2.move();

                /** BALL */

                if (ball == null) {
                    ball = Utils.startBall(player1, player2);
                }

                if (ball != null) {

                    if (ball.isStatic()) {
                        screenWriter.showStartBallText();
                    }

                    if (!ball.isStatic()) {
                        screenWriter.deleteStartBallText();
                        ball.move();
                    }

                    ball.draw();
                }

                /** COLLISIONS */

                CollisionDetector.ballCollidesWithWalls(ball, CANVAS); // Walls

                for (int i = 0; i < blocks.length; i++) { // Blocks
                    boolean collision = false;

                    if (blocks[i].isActive()) {
                        collision = CollisionDetector.ballCollidesWithBlocks(ball, blocks[i]);
                    }

                    if (collision) {
                        showBlock(chooseBlock());
                        break;
                    }
                }

                touchCount = CollisionDetector.ballCollidesWithPlayer(ball, player1,touchCount); // Player 1
                touchCount = CollisionDetector.ballCollidesWithPlayer(ball, player2,touchCount); // Player 2

                if (touchCount >= touchIncrement) {
                    touchCount = 0;
                    ballSpeed+=increment;
                    //ball.setSpeed(ballSpeed);
                }


                // check ball collision with goals
                roundWinner = Utils.checkVictoryCondition(ball, CANVAS, player1, player2);

                /** BLOCKS */

                for (Block block : blocks) {

                    if (block.isActive()) {
                        block.draw();
                    }
                }

                /** CHECKING WINNER */

                if (roundWinner.equals(PlayerNumber.ONE)) {
                    if (player2.getPoints() == 0) {
                        winner = roundWinner;
                        screenWriter.setWinner(winner);
                    }

                    screenWriter.updatePlayerScores();
                    ball.delete();
                    ball = null;
                }

                if (roundWinner.equals(PlayerNumber.TWO)) {
                    if (player1.getPoints() == 0) {
                        winner = roundWinner;
                        screenWriter.setWinner(winner);
                    }

                    screenWriter.updatePlayerScores();
                    ball.delete();
                    ball = null;
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Build matrix of blocks
     * <p>
     *
     * @params blockWidth   the width of blocks
     * @params blockHeigth  the height of blocks
     * @params blockCols    the total number of columns
     * @params blockRows    the total number of rows
     * @params padding      block padding, general setting to keep blocks padded inside CANVAS
     * @params canvasWidth  block padding, general setting to keep blocks inside Canvas
     * @return the array of built blocks
     */
    public Block[] blockMatrix(int blockWidth, int blockHeigth, int blockCols, int blockRows,
                               int padding, int canvasWidth) {

        Block[] blocks = new Block[blockCols * blockRows];
        int i = 0;

        for (int col = 0; col < blockCols; col++) {
            int x = padding + ((canvasWidth - (blockWidth * blockCols)) / 2) + (col * blockWidth);

            for (int row = 0; row < blockRows; row++) {
                int y = padding + (row * blockHeigth);


                blocks[i] = new Block(x, y, blockWidth, blockHeigth, new Picture(x, y, this.blockSkin));

                i++;
            }
        }
        return blocks;
    }

    /**
     * Activate a ramdom block from the block array to substitute a deactivated one
     * Returns the number of the choosen block
     */
    public int chooseBlock() {
        int block = 0;
        boolean found = false;
        while (!found) {
            block = (int) Math.floor(Math.random() * blocks.length);
            if (!blocks[block].isActive()) {
                blocks[block].setActive(true);
                found = true;
            }
        }
        return block;
    }

     /**
     * Activate a number of blocks randomly on the block array
     * <p>
     * Randonmly turns blocks on at the start of the game.
     *
     * @param numBlock the number of active blocks at that time
     */
    public void chooseBlocks(int numBlock) {
        int i = 0;

        if (numBlock <0) { // minimum number of blocks to activate
            numBlock = 1;
        }
        if (numBlock >= blocks.length) { // maximum number of blocks to activate
            numBlock = blocks.length;
        }
        while (i < numBlock) {
            int block = (int) Math.floor(Math.random() * blocks.length);

            if (!blocks[block].isActive()) {
                blocks[block].setActive(true);
                i++;
            }
        }
    }

    /**
     * Activate a block
     * Draws the block and sets it to active
     * @param block the block number to show
     */
    public void showBlock(int block) {
        if (block <0 || block >= blocks.length) { // control the number of blocks
            return;
        }
        blocks[block].getPicture().draw();
        blocks[block].setActive(true);
    }

    /**
     * Shows all active blocks
     */
    public void showBlocks() {

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].isActive()) {
                blocks[i].getPicture().draw();
            }
        }
    }

    /**
     * Hides all active blocks
     */
    public void hideBlocks() {

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].isActive()) {
                blocks[i].getPicture().delete();
            }
        }
    }

    public  static Ball getBall() {
        return ball;
    }
}

