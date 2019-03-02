package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Block;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;
import org.academiadecodigo.tropadelete.charlie.Utils.Utils;


public class Stage {
    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 768;
    private final int STAGE_WIDTH = 1680;

    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGTH = (STAGE_HEIGHT / 8)-1;

    private final int PADDLE_WALL_OFFSET = 40;
    private final int PLAYER1_OFFSET = PADDING + PADDLE_WALL_OFFSET;
    private final int PLAYER2_OFFSET = PADDING*2 + STAGE_WIDTH - PADDLE_WALL_OFFSET - BLOCK_WIDTH;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, STAGE_WIDTH, STAGE_HEIGHT);

    private final String RESOURCES ="resources/themes/";

    private Block[] blocks;
    private Player  player1;
    private Player  player2;
    private Ball    ball;

    private String backgroundSkin;
    private String paddleLeftSkin;
    private String paddleRightSkin;
    private String ballSkin;
    private String blockSkin;

    /**
     * Create and skin the stage
     * <p>
     * Randomly choose a combination of skins for the background, paddle ball and blocks
     */
    public Stage() {
        int theme  = 2; //(int) (Math.random() * 3);
        int option = (int) (Math.random() * 3);

        // só para testes
        if (theme == 2) {option = 0;}

        this.backgroundSkin  = RESOURCES+theme+"/images/background" +option+".png";
        this.paddleLeftSkin  = RESOURCES+theme+"/images/paddleLeft" +option+".png";
        this.paddleRightSkin = RESOURCES+theme+"/images/paddleRight"+option+".png";
        this.ballSkin        = RESOURCES+theme+"/images/ball"       +option+".png";
        this.blockSkin       = RESOURCES+theme+"/images/block"      +option+".png";

        Picture background = new Picture(PADDING, PADDING, this.backgroundSkin);
        background.draw();
    }

    /**
     * Initiate the Game
     * <p>
     * Draw the CANVAS, create the players on each side of the screen
     */
    public void init() {
        CANVAS.draw();

        player1 = new Player(PLAYER1_OFFSET, PADDING+((STAGE_HEIGHT-150)/2), STAGE_HEIGHT, paddleLeftSkin ); // LEFT
        player2 = new Player(PLAYER2_OFFSET, PADDING+((STAGE_HEIGHT-150)/2), STAGE_HEIGHT, paddleRightSkin); // RIGHT

        new KeyboardListener(player1, player2);
        makeBlocks(15, 8);
    }

    /**
     * Block factory
     * <p>
     * Generates blocks in rows and columns
     * @params blockCols
     * @params blockRows
     */
    public void makeBlocks(int blockCols, int blockRows) {
        blocks = blockMatrix(BLOCK_WIDTH, BLOCK_HEIGTH, blockCols, blockRows, PADDING, STAGE_WIDTH);
        chooseBlock(20);
        showBlocks();
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
                    ball = Utils.startBall(CANVAS);
                }
                ball.move();
                ball.draw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Build matrix of blocks
     * <p>
     * @params blockWidth   block width
     * @params blockHeigth  block height
     * @params blockCols    block columns
     * @params blockRows    block rows, number of rows of blocks
     * @params padding      block padding, general setting to keep blocks padded inside CANVAS
     * @params canvasWidth  block padding, general setting to keep blocks inside Canvas
     */
    public Block[] blockMatrix(int blockWidth, int blockHeigth, int blockCols, int blockRows,
                               int padding, int canvasWidth) {

        Block[] blocks = new Block[blockCols * blockRows];
        int i = 0;

        for (int col = 0; col < blockCols; col++) {
            int x = padding + ((canvasWidth - (blockWidth * blockCols)) / 2) + (col * blockWidth);

            for (int row = 0; row < blockRows; row++) {
                int y = padding + (row * blockHeigth);

                blocks[i] = new Block(x, y, blockWidth, blockHeigth,
                            new Picture(x, y, this.blockSkin));

                i++;
            }
        }
        return blocks;
    }

    /**
     * Turns blocks on and off
     * <p>
     * Randonmly turns blocks on at the start of the game.
     * @param numBlock the number of active blocks at that time
     */
    public void chooseBlock(int numBlock) {
        int i = 0;

        while (i < numBlock) {
            int r = (int) Math.floor(Math.random() * blocks.length);

            if (!blocks[r].isActive()) {
                blocks[r].setActive();
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
                Picture picture = blocks[i].getPicture();
                picture.draw();
            }
        }
    }

     /**
     * Shows the active blocks
     * <p>
     * Draws all the active blocks
     */
    public void hideBlocks() {

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].isActive()) {
                Picture picture = new Picture(blocks[i].getPositionX(), blocks[i].getPositionY(), this.blockSkin);
                picture.draw();
            }
        }
    }
}

