import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player implements Drawable {

    private int posY;
    private int step;
    private Direction direction;
    private boolean hasLost;

    private Rectangle paddle;
    private int minHeight;
    private int maxHeight;
    private int paddleHeight;
    private int stageHeight;

    public Player(int offset, int posY, int stageHeight) {
        this.step = 30;
        this.minHeight = stageHeight;
        this.hasLost = false;
        this.paddleHeight = 150;
        this.maxHeight = stageHeight - this.paddleHeight;
        this.posY = (this.minHeight / 2) + (this.paddleHeight / 2);
        this.paddle = generatePaddle(offset);
    }


    public Rectangle generatePaddle(int x) {

        Rectangle paddle = new Rectangle(x, posY, 10, 150);
        paddle.setColor(Color.GREEN);
        paddle.fill();
        return paddle;
    }

    public void move() {

        //use switch!!!

        if (this.direction == Direction.UP) {
            System.out.println(posY);

            this.posY -= step;
            this.paddle.translate(0, -step);
        }

        if (this.direction == Direction.DOWN) {
            System.out.println(posY);

            this.posY += step;
            this.paddle.translate(0, step);
        }

        setDirection(null);
    }

    public void draw() {
        paddle.draw();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}

