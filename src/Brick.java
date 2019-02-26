import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private int x;
    private int y;
    private boolean isHit;
    private Rectangle rectangle;

    public Brick(){
        rectangle = new Rectangle();
        rectangle.setColor(Color.BLUE);
        rectangle.fill();
    }


    public int getPositionX(){
        return x;
    }

    public int getPositionY(){
        return y;
    }

    public void setPositionX(int x){
        this.x = x;
    }

    public void setPositionY(int y){
        this.y = y;
    }

    public void setHit(){
        this.isHit = true;
        rectangle.delete();
    }

    public void respawn(){
        this.isHit = false;
        rectangle.fill();
    }
}
