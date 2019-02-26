import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.awt.*;

public class Player implements Drawable {
    private Rectangle paddle;
    private int step;
    private int posY;
    private int stageHeight;
    private int paddleHeight;
    private boolean hasLost;
    private int minHeight;
    private int maxHeight;
    private Direction direction;

    public Player(int offset, int posY, int stageHeight) {
        this.step = 30;
        this.minHeight = stageHeight;
        this.hasLost = false;
        this.paddleHeight = 150;
        this.maxHeight = stageHeight - this.paddleHeight;
        this.posY = (this.minHeight / 2) + (this.paddleHeight / 2);
        this.paddle = generatePaddle(offset);

    }

    public void move() {

        //use switch!!!

        if (this.direction == Direction.UP) {
            System.out.println(posY);
            if(posY <= 0){
                posY = 0;
            }

            this.posY -= step;
            this.paddle.translate(0, step * -1);
        }
        if (this.direction == Direction.DOWN) {
            System.out.println(posY);
            if(posY >= minHeight){
                posY = 0;
            }

            this.posY += step;
            this.paddle.translate(0, step);
        }
        setDirection(null);


    }

    public Rectangle generatePaddle(int x) {
        Rectangle paddle = new Rectangle(x, posY, 10, 150); //startx,starty,width,height
        paddle.setColor(Color.GREEN);
        paddle.fill();
        return paddle;
    }


    public void draw() {
        paddle.draw();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}

