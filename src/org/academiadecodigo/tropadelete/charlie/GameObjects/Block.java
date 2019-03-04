package org.academiadecodigo.tropadelete.charlie.GameObjects;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Block {

    private int       x;
    private int       y;
    private int       width;
    private int       heigth;
    private boolean   active;
    private boolean   isHit;
    private Rectangle rectangle;
    private Picture   picture;

    public Block(int x, int y, int width, int heigth, Picture picture) {
        this.x = x;
        this.y = y;
        this.width  = width;
        this.heigth = heigth;
        this.active = false;
        rectangle = new Rectangle(x,y,width,heigth);
        this.picture = picture;

    }
    /** For testing purposes. */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x, y, 60, 60);
        rectangle.setColor(Color.BLUE);
        rectangle.fill();
    }

    public void setActive(boolean set) {
        this.active = set;
    }
    public boolean isActive(){
        return active;
    }


    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public Picture getPicture() {
        return this.picture;
    }

    /**
     * Deactivate a block
     * Hides a block and sets it to false
     */
    public void hideBlock() {

        this.getPicture().delete();
        this.setActive(false);
    }
}
