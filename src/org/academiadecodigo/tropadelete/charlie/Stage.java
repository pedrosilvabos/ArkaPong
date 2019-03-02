package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Block;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;
import org.academiadecodigo.tropadelete.charlie.Utils.Utils;


public class Stage {
<<<<<<< HEAD

    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 500;
    private final int player1_OFFSET = 30;
    private final int player2_OFFSET = 1260;

    private Brick[] bricks;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean gameEnd;
=======
    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 768;
    private final int STAGE_WIDTH = 1680;

    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGTH = (STAGE_HEIGHT / 8)-1;

    private final int PADDLE_WALL_OFFSET = 30;
    private final int PLAYER1_OFFSET = PADDLE_WALL_OFFSET;
    private final int PLAYER2_OFFSET = STAGE_WIDTH - PADDLE_WALL_OFFSET;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, STAGE_WIDTH, STAGE_HEIGHT);
>>>>>>> f0ee7dbda49ef7652819601b85254cbbc3dbe0cf

    private Block[] blocks;
    private Player player1;
    private Player player2;
    private Ball ball;
    private String backgroundSkin;
    private String paddleSkin;
    private String ballSkin;
    private String blockSkin;

    /**
     * Create and skin the stage
     * <p>
     * Randomly choose a combination of skins for the background, paddle ball and blocks
     */
    public Stage() {
        int theme = (int) (Math.random() * 3);
        System.out.println(theme);
        switch (theme) {
            case 0:
                this.backgroundSkin = "resources/background0.png";
                this.paddleSkin = "resources/paddle.png";
                this.ballSkin = "resources/ball0.png";
                this.blockSkin = "resources/block0.png";
                break;
            case 1:
                this.backgroundSkin = "resources/background1.png";
                this.paddleSkin = "resources/paddle.png";
                this.ballSkin = "resources/ball1.png";
                this.blockSkin = "resources/block1.png";
                break;
            case 2:
                this.backgroundSkin = "resources/background2.png";
                this.paddleSkin = "resources/paddle.png";
                this.ballSkin = "resources/ball1.png";
                this.blockSkin = "resources/block2.png";
                break;

        }
        Picture background = new Picture(PADDING, PADDING, this.backgroundSkin);
        background.draw();
    }

    /**
     * Initiate the Game
     * <p>
     * Draw the CANVAS, create the players on each side of the screen
     */
    public void init() {
<<<<<<< HEAD

        Rectangle canvas = new Rectangle(PADDING, PADDING, 1280, 768);
        canvas.draw();

=======
        CANVAS.draw();
        player1 = new Player(PLAYER1_OFFSET, PADDING, STAGE_HEIGHT);
        player2 = new Player(PLAYER2_OFFSET, PADDING, STAGE_HEIGHT);
>>>>>>> f0ee7dbda49ef7652819601b85254cbbc3dbe0cf
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
        chooseBlock(60);
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

                blocks[i] = new Block(x, y, blockWidth, blockHeigth);
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
                Picture picture = new Picture(blocks[i].getPositionX(), blocks[i].getPositionY(), this.blockSkin);
                picture.draw();
            }
        }
    }
}

