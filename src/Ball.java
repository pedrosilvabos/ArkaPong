import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
public class Ball {
    private int positionX;
    private int positionY;
    private Ellipse ball;

    //initialize the ball with a motion
    //ball starts at the center of the screen
    public Ball(int positionX, int positionY){
        this.positionX = 0;
        this.positionY = 0;
        this.ball = new Ellipse(300,300,30,30);
    }
    //must know the motion of the ball
    public void move(){
        this.ball.setColor(Color.BLACK);
        this.ball.fill();
        this.positionX = this.ball.getX();
        this.positionX = this.ball.getY();
        this.ball.draw();
    }


}
