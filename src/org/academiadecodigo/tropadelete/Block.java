package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Block implements Drawable {

    private int x;
    private int y;
    private boolean isHit;
    private Rectangle rectangle;

    public Block(){
        rectangle = new Rectangle();
        rectangle.setColor(Color.BLUE);
        rectangle.fill();
    }

    /** For testing purposes. */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x, y, 60, 60);
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isHit() {
        return isHit;
    }

    public void draw() {
        rectangle.fill();
    }
}
