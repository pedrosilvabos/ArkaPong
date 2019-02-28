package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Stage {
    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 500;
    private final int STAGE_WIDTH = 1500;

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
                "resources/background2.jpg",
                "resources/background3.jpg"
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
}

