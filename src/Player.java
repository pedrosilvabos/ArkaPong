import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.awt.*;

public class Player {
    private int step;
    private int posY;
    private int paddleHeight;
    private boolean hasLost;
    private int minHeight;
    private int maxHeight;

    public Player(int posX, int posY, int stageHeight) {
        this.minHeight = stageHeight;
        this.hasLost = false;
        this.paddleHeight = 150;
        this.maxHeight = stageHeight - this.paddleHeight;
        this.posY = (this.minHeight/2)+(this.paddleHeight/2);

    }

    public void move() {
        this.posY += this.step;
    }

    public Rectangle generatePaddle(int x) {
        Rectangle paddle = new Rectangle(x, posY, 10,150); //startx,starty,width,height

        paddle.setColor(Color.GREEN);
        paddle.fill();
        return paddle;
    }
public void setPosY(int step){
        this.posY +=step;
}
}

