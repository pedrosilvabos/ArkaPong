import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;3
public class Stage {

    private int numberOfBricks;
    private Player player1;
    private Player player2;
    private Ball ball;

    public Stage(){
        player1 = new Player();
        player2 = new Player();
    }


    //Brick Generator
    public Brick[] generateBricks(int numberOfBricks){
        Brick[] bricks = new Brick[numberOfBricks];
        for(int i = 0; i<bricks.length;i++){
            bricks[i] = new Brick();
        }
        return bricks;
    }

}

