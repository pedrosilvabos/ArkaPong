public class Brick {

    private int positionX;
    private int positionY;
    private boolean isHit;

    public Brick(){
        this.isHit = false;
    }

    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }

    public void setPositionX(int x){
        this.positionX = x;
    }
    public void setPositionY(int y){
        this.positionY = y;
    }

    public void setHit(){
        this.isHit = true;
    }
    public void respawn(){
        this.isHit = false;
    }
}
