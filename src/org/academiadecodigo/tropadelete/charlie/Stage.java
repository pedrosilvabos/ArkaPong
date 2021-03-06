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
    private final int BLOCK_HEIGHT = (STAGE_HEIGHT / 8) - 1;

    private final int PADDLE_WALL_OFFSET = 30;
    private final int PLAYER1_OFFSET = PADDING     + PADDLE_WALL_OFFSET;
    private final int PLAYER2_OFFSET = STAGE_WIDTH - PADDLE_WALL_OFFSET - 25;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, STAGE_WIDTH, STAGE_HEIGHT);

    private final int touchIncrement =  5;
    private final int blockIncrement =  1;
    private final int initialSleep   = 25;
    private       int sleep          = initialSleep;
    private       int sleepDecrement = -2;
    private       int numBlocks      =  5;

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

    private Sound music;
    private Sound blockHide;
    private Sound blockShow;
    private Sound paddleHit;
    private Sound victory;

    /**
     * Create and skin the stage
     * <p>
     * Randomly choose a combination of skins for the background, paddle ball and blocks
     */
    public Stage() {

        int theme  = (int) (Math.random() * 3);
        int option = 0; //(int) (Math.random() * 3);
        this.music      = new Sound("/resources/themes/"+theme+"/sounds/music.wav");
        this.blockHide  = new Sound("/resources/themes/"+theme+"/sounds/blockHide.wav");
        this.blockShow  = new Sound("/resources/themes/"+theme+"/sounds/blockShow.wav");
        this.paddleHit  = new Sound("/resources/themes/"+theme+"/sounds/paddleHit.wav");
        this.victory    = new Sound("/resources/themes/"+theme+"/sounds/victory.wav");


        music.setLoop(1);
        music.play(true);

        if (theme == 2) {option = 0;}

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
        chooseBlock(10);
        showBlocks();
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

        blocks = blockMatrix(BLOCK_WIDTH, BLOCK_HEIGHT, blockCols, blockRows, PADDING, STAGE_WIDTH);

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
                Thread.sleep(sleep);

                /** PLAYERS */

                screenWriter.drawPlayerScores();

                if (player1.isMoving()) {
                    player1.move();
                }

                if(player2.isMoving()) {
                    player2.move();
                }

                /** BALL */

                if (ball == null) {
                    ball = Utils.startBall(player1, player2,this.ballSkin);
                    sleep = initialSleep;
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

                CollisionDetector.ballCollidesWithWalls(ball, CANVAS);

                for (int i = 0; i < blocks.length; i++) {
                    boolean collision = false;

                    if (blocks[i].isActive()) {

                        collision = CollisionDetector.ballCollidesWithBlocks(ball, blocks[i]);

                    }

                    if (collision) {
                        //destroy the block
                        blockHide.play(true);
                        break;
                    }
                }

                touchCount = CollisionDetector.ballCollidesWithPlayer(ball, player1,touchCount,paddleHit);
                touchCount = CollisionDetector.ballCollidesWithPlayer(ball, player2,touchCount,paddleHit);


                if (touchCount >= touchIncrement) {
                    touchCount = 0;
                    this.numBlocks += blockIncrement;
                    chooseBlock(numBlocks);
                    showBlocks();
                    sleep += sleepDecrement;
                    if (sleep < 1) {sleep = 1;}
                }


                // check ball collision with goals
                roundWinner = Utils.checkVictoryCondition(ball, CANVAS, player1, player2);


                /** CHECKING WINNER */

                if (roundWinner.equals(PlayerNumber.ONE)) {
                    if (player2.getPoints() == 0) {
                        winner = roundWinner;
                        screenWriter.setWinner(winner);
                        victory.play(true);
                    }

                    screenWriter.updatePlayerScores();
                    ball.delete();
                    ball = null;
                }

                if (roundWinner.equals(PlayerNumber.TWO)) {
                    if (player1.getPoints() == 0) {
                        winner = roundWinner;
                        screenWriter.setWinner(winner);
                        victory.play(true);
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
     * Turns blocks on and off
     * <p>
     * Randonmly turns blocks on at the start of the game.
     *
     * @param numBlock the number of active blocks at that time
     */
    public void chooseBlock(int numBlock) {
        hideBlocks();
        int i = 0;

        while (i < numBlock) {
            int r = (int) Math.floor(Math.random() * blocks.length);

            if (!blocks[r].isActive()) {
                blocks[r].setActive(true);
                i++;
            }
        }
    }

    /**
     * Shows the active blocks
     * <p>
     * Draws all the active blocks
     */
    public void showBlocks() {

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].isActive()) {
                blockShow.play(true);
                blocks[i].getPicture().draw();
            }
        }
    }

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

