import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Stage {
    private final int PADDING = 10;
    private Brick[] brick;
    private Player player1;
    private Player player2;
    private Ball ball;
    private Canvas rectangle;
    private boolean gameEnd;

    private final int STAGE_WIDTH = 1024;
    private final int STAGE_HEIGHT = 500;
    private final int player1_OFFSET = 30;
    private final int player2_OFFSET = 1004;


    public Stage() {
        player1 = new Player(10, 10,STAGE_HEIGHT);
        player2 = new Player(50, 10,STAGE_HEIGHT);
        ball = new Ball(10, 10);
    }


    /* Initial method:
        Create Stage
        Create Paddles + make paddles work
        Create Ball + make ball bounce around
    */
    public void init() {
        Rectangle canvas = new Rectangle(PADDING,PADDING,1024,768);
        canvas.draw();

        Rectangle paddle = player1.generatePaddle(player1_OFFSET);
        Rectangle paddle2 = player2.generatePaddle(player2_OFFSET);

        //ball.move();


        new KeyboardListener(player1, player2);
    }
    public void start(){

    }

}

