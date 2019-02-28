package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;



public class Stage {

    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 500;
    private final int player1_OFFSET = 30;
    private final int player2_OFFSET = 1260;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, 1280, 768);

    private Block[] bricks;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean gameEnd;


    public Stage() {
        player1 = new Player(player1_OFFSET, 10, STAGE_HEIGHT);
        player2 = new Player(player2_OFFSET, 10, STAGE_HEIGHT);
        ball = new Ball(10, 10);

    }


    public void init() {

        CANVAS.draw();
        new KeyboardListener(player1, player2);
    }

    public void start() {

        while (true) {
            try {
                Thread.sleep(0);
                player1.move();
                player2.move();

                if (!ball.exists()) {
                    ball =
                }

                ball.move();
                ball.draw();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

}

