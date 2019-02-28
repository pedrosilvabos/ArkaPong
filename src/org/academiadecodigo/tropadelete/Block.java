package org.academiadecodigo.tropadelete;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Block {

    private int     x;
    private int     y;
    private int     width;
    private int     heigth;
    private boolean active;
    private Rectangle rectangle;

    public Block(int x, int y, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.width  = width;
        this.heigth = heigth;
        this.active = false;


    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeigth() {
        return this.heigth;
    }

    public void setPositionX(int x) {
        this.x = x;
    }

    public void setPositionY(int y) {
        this.y = y;
    }

    public void setActive() {
        this.active = true;
        //rectangle.delete();
    }
    public boolean isActive(){
        return active;
    }

    public void respawn() {
        this.active = true;
        rectangle.fill();
    }

}
