package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player implements Drawable {

    private int posY;
    private int step;
    private PlayerDirection playerDirection;
    private boolean hasLost;

    private PlayerNumber playerNumber;
    private Rectangle paddle;
    private int minHeight;
    private int maxHeight;
    private int paddleHeight;
    private int stageHeight;

    private int maxPaddleYTravel;

    public Player(int offset, int posY, int stageHeight, PlayerNumber playerNumber) {
        this.step = 12;
        this.minHeight = stageHeight;
        this.hasLost = false;
        this.paddleHeight = 150;
        this.maxHeight = stageHeight - this.paddleHeight;
        this.posY = (this.minHeight / 2) + (this.paddleHeight / 2);
        this.paddle = generatePaddle(offset);
        this.playerNumber = playerNumber;
    }


    public Rectangle generatePaddle(int x) {

        Rectangle paddle = new Rectangle(x, posY, 25, 150);
        paddle.setColor(Color.GREEN);
        paddle.fill();
        return paddle;
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    public void move() {

        //use switch!!!

        if (this.playerDirection == PlayerDirection.UP) {

            this.posY -= step;

            if(posY < 13){              //CHECK THIS SHIT AND PUT IN PROPERTIES
                this.posY = 13;

            }else{
                this.paddle.translate(0, -step);
            }
        }
        if (this.playerDirection == PlayerDirection.DOWN) {

            this.posY += step;
            System.out.println(posY);
            if(posY > 636){              //CHECK THIS SHIT AND PUT IN PROPERTIES
                this.posY = 636;

            }else {
                this.paddle.translate(0, step);
            }
        }

        setPlayerDirection(null);
    }

    public Rectangle getRectangle() {
        return paddle;
    }

    public void draw() {
        paddle.draw();
    }

    public void setPlayerDirection(PlayerDirection playerDirection) {
        this.playerDirection = playerDirection;
    }

}