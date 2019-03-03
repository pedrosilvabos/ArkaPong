package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
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

    private final String pressStart = " < Press UP/DOWN key to start_> ";
    private Rectangle backgroundRect;
    private Text initMessage;


    /**
     * Create and skin the stage
     * <p>
     * Randomly choose a combination of skins for the background, paddle ball and blocks
     */
    public Stage() {
        int theme  = (int) (Math.random() * 3);
        int option = 0; //(int) (Math.random() * 3); // fixed option 0 for first version

        // só para testes
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
        hideBlocks();

        /*
        // For testing purposes!
        for (int i = 0 ; i < blocks.length ; i++) {
            blocks[i] = new Block(((1280 / 2) - 300) + (i * 30), (768 / 2) + 20);
        }
        */
        initMessage = new Text(PADDING + (STAGE_WIDTH / 2) - 60, PADDING + (STAGE_HEIGHT / 2) + 30, pressStart );
        initMessage.grow(20, 20);
        initMessage.setColor(Color.WHITE);

        backgroundRect = new Rectangle(initMessage.getX() - 10, initMessage.getY() - 10, initMessage.getWidth() - 20, initMessage.getHeight() + 10);

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
        chooseBlock(20);
        showBlocks();
        hideBlocks();
    }

    /**
     * Start the game
     * <p>
     * Initiate the thread of the game
     */
    public void start() {

        while (true) {

            try {

                Thread.sleep(20);
                player1.move();
                player2.move();

                if (ball == null) {
                    ball = Utils.startBall(player1, player2);
                }

                CollisionDetector.ballCollidesWithWalls(ball, CANVAS);

                for (Block block : blocks) {

                    if (!block.isHit()) {
                        CollisionDetector.ballCollidesWithBlocks(ball, block);
                    }
                }

                touchCount = CollisionDetector.ballCollidesWithPlayer(ball, player1,touchCount);
                touchCount = CollisionDetector.ballCollidesWithPlayer(ball, player2,touchCount);

                if (touchCount >= touchIncrement) {
                    touchCount = 0;
                    ballSpeed+=increment;
                    //ball.setSpeed(ballSpeed);
                }

                for (Block block : blocks) {

                    if (!block.isHit()) {
                        block.draw();
                    }
                }
                if (ball != null) {

                    if (ball.isStatic()) {
                        backgroundRect.fill();
                        initMessage.draw();
                    }

                    if (!ball.isStatic()) {
                        backgroundRect.delete();
                        initMessage.delete();
                        ball.move();
                    }

                    ball.draw();
                }



                if (Utils.checkVictoryCondition(ball, CANVAS, player1, player2)) {
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
     * @params blockWidth   block width
     * @params blockHeigth  block height
     * @params blockCols    block columns
     * @params blockRows    block rows, number of rows of blocks
     * @params padding      block padding, general setting to keep blocks padded inside CANVAS
     * @params canvasWidth  block padding, general setting to keep blocks inside Canvas
     */
    public Block[] blockMatrix(int blockWidth, int blockHeigth, int blockCols, int blockRows,
                               int padding,    int canvasWidth) {

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

