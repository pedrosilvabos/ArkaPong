import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {
    private int posX;
    private int posY;
    private int width;
    private boolean hasLost;

    public Player(int posX, int posY) {

        this.hasLost = false;

    }

    //comes from keyboard listener
    public void move() {

    }

    public Picture generatePaddle(int x) {
        Picture paddle = new Picture(x, 0, "resources/paddle.png");
        paddle.draw();
        return paddle;
    }
public void setPosY(){
        this.posY +=10;
}
}
