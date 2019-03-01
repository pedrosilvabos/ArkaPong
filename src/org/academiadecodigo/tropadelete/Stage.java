package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;



public class Stage {

    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 500;
    private final int player1_OFFSET = 30;
    private final int player2_OFFSET = 1260;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, 1280, 768);

    private Block[] blocks = new Block[3];
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean gameEnd;


    public Stage() {

        player1 = new Player(player1_OFFSET, 10, STAGE_HEIGHT, PlayerNumber.ONE);
        player2 = new Player(player2_OFFSET, 10, STAGE_HEIGHT, PlayerNumber.TWO);
        //ball = new Ball(10, 10);


        /** For testing purposes! */
        for (int i = 0 ; i < blocks.length ; i++) {
            blocks[i] = new Block(((1280 / 2) - 300) + (i * 30), (768 / 2) + 20);
        }

    }


    public void init() {

        CANVAS.draw();
        new KeyboardListener(player1, player2);
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

                CollisionDetector.ballCollidesWithWalls(ball, CANVAS);

                for (Block block : blocks) {

                    if (!block.isHit()) {
                        CollisionDetector.ballCollidesWithBlocks(ball, block); /** Testing this now. */
                    }
                }

                CollisionDetector.ballCollidesWithPlayer(ball, player1);
                CollisionDetector.ballCollidesWithPlayer(ball, player2);
                for (Block block : blocks) {
                    if (!block.isHit()) {
                        block.draw();
                    }
                }

                ball.move();
                ball.draw();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

}

