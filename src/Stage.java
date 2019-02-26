import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Stage {

    private final int STAGE_WIDTH = 1024;
    private final int STAGE_HEGHT = 500;
    private final int player1_OFFSET = 10;
    private final int player2_OFFSET = 986;
    private int numberOfBricks;
    private Player player1;
    private Player player2;
    private Ball ball;

    public Stage() {
        player1 = new Player(10, 10);
        player2 = new Player(50, 10);
        ball = new Ball(10, 10);
    }


    //Brick Generator
    public Brick[] generateBricks(int numberOfBricks) {
        Brick[] bricks = new Brick[numberOfBricks];
        for (int i = 0; i < bricks.length; i++) {
            bricks[i] = new Brick();
        }
        return bricks;
    }

    /* Initial method:
        Create Stage
        Create Paddles + make paddles work
        Create Ball + make ball bounce around
    */
    public void init() {
        Picture canvas = new Picture(10, 0, "resources/bg.jpg");
        canvas.draw();

        Picture paddle = player1.generatePaddle(player1_OFFSET);
        Picture paddle2 = player2.generatePaddle(player2_OFFSET);

        ball.move();

        new KeyboardListener(player1, player2);
    }

}

