package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Stage {
    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 768;
    private final int STAGE_WIDTH = 1680;

    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGTH = 100;

    private final int PADDLE_WALL_OFFSET = 30;
    private final int PLAYER1_OFFSET = PADDLE_WALL_OFFSET;
    private final int PLAYER2_OFFSET = STAGE_WIDTH - PADDLE_WALL_OFFSET;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, STAGE_WIDTH, STAGE_HEIGHT);

    private Block[] blocks;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean gameEnd;
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
        int theme = (int) (Math.random() * 4);
        System.out.println(theme);
        switch (theme) {
            case 1:
                this.backgroundSkin = "resources/background0.png";
                this.paddleSkin = "resources/paddle.png";
                this.ballSkin = "resources/ball0.png";
                this.blockSkin = "resources/block0.png";
                break;
            case 2:
                this.backgroundSkin = "resources/background1.png";
                this.paddleSkin = "resources/paddle.png";
                this.ballSkin = "resources/ball1.png";
                this.blockSkin = "resources/block1.png";
                break;
            case 3:
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
     * Draw the CANVAS, create the players on each side of the screen and limit their paddle motion.
     * PADDING so they dont got over the top of the CANVAS;
     * STAGE_HEIGHT so they dont got under the CANVAS;
     */
    public void init() {
        CANVAS.draw();
        player1 = new Player(PLAYER1_OFFSET, PADDING, STAGE_HEIGHT);
        player2 = new Player(PLAYER2_OFFSET, PADDING, STAGE_HEIGHT);
        new KeyboardListener(player1, player2);
        makeBlocks(15, 5);
    }

    /**
     * Block factory
     * <p>
     * Generates blocks in rows and columns
     */
    public void makeBlocks(int blockCols, int blockRows) {
        blocks = blockMatrix(BLOCK_WIDTH, BLOCK_HEIGTH, blockCols, blockRows, PADDING, STAGE_WIDTH, STAGE_HEIGHT);
        chooseBlock(60);
        showBlocks();
    }

    /**
     * Start the game
     * <p>
     * Initiate the thread of the game that will run in cycles redrawing everything every iteration
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
     * OBS: need some work, fine tuning and twiks - RA
     */
    public Block[] blockMatrix(int blockWidth, int blockHeigth, int blockCols, int blockRows,
                               int padding, int canvasWidth, int canvasHeigth) {

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

    public void showBlocks() {

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].isActive()) {
                Picture picture = new Picture(blocks[i].getPositionX(), blocks[i].getPositionY(), this.blockSkin);
                picture.draw();
            }
        }
    }
}

