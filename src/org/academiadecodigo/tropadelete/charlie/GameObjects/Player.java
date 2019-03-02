package org.academiadecodigo.tropadelete.charlie.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.charlie.Utils.Drawable;

public class Player implements Drawable {

    private int posY;
    private int step;
    private Direction direction;
    private boolean hasLost;

    private Rectangle paddle;
    private Picture paddleSkin;
    private int minHeight;
    private int maxHeight;
    private int paddleHeight;
    private int playerOffset;

    public Player(int offset, int posY, int stageHeight, String paddleSkin) {
        this.step = 12; //paddle jumps
        this.playerOffset = offset;
        this.minHeight = stageHeight;
        this.hasLost = false;
        this.paddleHeight = 150;
        this.maxHeight = stageHeight - this.paddleHeight;
        this.posY = posY; //(this.minHeight / 2) + (this.paddleHeight / 2);
        this.paddle = generatePaddle();
        this.paddleSkin = skinPaddle(paddleSkin);
    }

    public Rectangle generatePaddle() {

        Rectangle paddle = new Rectangle(this.playerOffset, posY, 25, 150);

        return paddle;
    }

    public Picture skinPaddle(String paddleSkin) {
        //make pictures names number and random them
        Picture paddle = new Picture(this.playerOffset, posY, paddleSkin);

        return paddle;
    }

    public void move() {

        //use switch!!!
        if (this.direction == Direction.UP) {
            this.posY -= step;
            if (posY < 13) {              //CHECK THIS SHIT AND PUT IN PROPERTIES
                this.posY = 13;
            } else {
                this.paddle.translate(0, -step);
                this.paddleSkin.delete();
                this.paddleSkin.translate(0, -step);
                this.paddleSkin.draw();
            }
        }
        if (this.direction == Direction.DOWN) {
            this.posY += step;

            if (posY > 636) {              //CHECK THIS SHIT AND PUT IN PROPERTIES
                this.posY = 636;
            } else {
                this.paddle.translate(0, step);
                this.paddleSkin.delete();
                this.paddleSkin.translate(0, step);
                this.paddleSkin.draw();
            }
        }
        setDirection(null);
    }

    public void draw() {
        paddle.draw();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getPosY(){
        return posY;
    }

}