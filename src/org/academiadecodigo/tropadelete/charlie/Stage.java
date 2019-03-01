package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Stage {
    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 500;
    private final int STAGE_WIDTH = 1500;


    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGTH = 100;

    private final int PADDLE_WALL_OFFSET = 30;
    private final int PLAYER1_OFFSET = PADDLE_WALL_OFFSET;
    private final int PLAYER2_OFFSET = STAGE_WIDTH - PADDLE_WALL_OFFSET;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, STAGE_WIDTH, 768);

    private int counter = 0;
    private Block[] blocks;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean gameEnd;

    private String backgroundName;

    public Stage() {
        int random = (int) (Math.random() * 2);

        String[] backgrounds = {
                "resources/background2.png",
                "resources/background3.png"
        };
        this.backgroundName = backgrounds[random];
        Picture background = new Picture(PADDING, PADDING, this.backgroundName);
        background.draw();
    }

    public void init() {
        CANVAS.draw();
        player1 = new Player(PLAYER1_OFFSET, PADDING, STAGE_HEIGHT);
        player2 = new Player(PLAYER2_OFFSET, PADDING, STAGE_HEIGHT);
        new KeyboardListener(player1, player2);

        // build matrix
        blocks = blockMatrix(BLOCK_WIDTH, BLOCK_HEIGTH, 15, 8, PADDING, STAGE_WIDTH, STAGE_HEIGHT);
        chooseBlock(60);
        showBlocks();
    }

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
     * OBS: need some work, fine tunning and twiks - RA
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

                // draw the block
                Rectangle rectangle = new Rectangle(blocks[i].getPositionX(), blocks[i].getPositionY(),
                        blocks[i].getWidth(), blocks[i].getHeigth());
                rectangle.setColor(Color.RED); // need ramdom color here
                rectangle.fill();

                // make border around the block
                Rectangle border = new Rectangle(blocks[i].getPositionX(), blocks[i].getPositionY(),
                        blocks[i].getWidth(), blocks[i].getHeigth());
                border.setColor(Color.WHITE);
                border.draw();
            }
        }
    }
}

