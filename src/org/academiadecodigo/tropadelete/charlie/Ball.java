package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Ball {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int speed;
    private Rectangle rectangle;
    
    //initialize the ball with a motion
    //ball starts at the center of the screen
 public Ball(int x, int y){
     this.x = x;
     this.y = y;
 }
    //must know the motion of the ball
    public void move(){
        x += dx;
        y += dy;
    }

}
