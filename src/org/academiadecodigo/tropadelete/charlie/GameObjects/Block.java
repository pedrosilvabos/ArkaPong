package org.academiadecodigo.tropadelete.charlie.GameObjects;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Block {

    private int     x;
    private int     y;
    private int     width;
    private int     heigth;
    private boolean active;
    private Rectangle rectangle;

    private Picture picture;

    public Block(int x, int y, int width, int heigth, Picture picture) {
        this.x = x;
        this.y = y;
        this.width  = width;
        this.heigth = heigth;
        this.active = false;
        this.picture = picture;


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

    public Picture getPicture() {
        return this.picture;
    }
}
