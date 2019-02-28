package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Stage {
    //there is a bug with the width and the stage height!!
    private final int PADDING = 10;
    private final int STAGE_HEIGHT = 500;
    private final int player1_OFFSET = 30;
    private final int player2_OFFSET = 1260;
    private final Rectangle CANVAS = new Rectangle(PADDING, PADDING, 1280, 768);

    private int counter = 0;
    private Block[] blocks;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean gameEnd;

    public void init() {

        CANVAS.draw();
        player1 = new Player(player1_OFFSET, 10, STAGE_HEIGHT);
        player2 = new Player(player2_OFFSET, 10, STAGE_HEIGHT);
        new KeyboardListener(player1, player2);
    }
//this is just an idea
    public void backgroundChanger() {

            int random = (int) (Math.random() * 2);
            System.out.println(random);
            System.out.println("change the backgroung");
            String[] backgrounds = {
                    "resources/background1.jpg",
                    "resources/background2.jpg"
            };
            Picture background = new Picture(PADDING, PADDING, backgrounds[random]);
            background.draw();
            player1.draw();
            player2.draw();

    }

    public void start() {

        while (true) {
            try {
                Thread.sleep(1);
                player1.move();
                player2.move();
                if (ball == null) {
                    ball = Utils.startBall(CANVAS);
                }
            this.counter += 1;
                System.out.println(counter);
                if(this.counter == 1000){
                   // backgroundChanger();
                    this.counter = 0;
                }
                ball.move();
                ball.draw();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

