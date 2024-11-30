package com.bird.main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cloud class
 */
public class Cloud {

    private BufferedImage img;
    private int speed;
    private int x,y;

    public Cloud(){}

    public Cloud(BufferedImage img, int speed, int x, int y) {
        this.img = img;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        x-=speed;
        g.drawImage(img,x,y,null);
    }

    public boolean isOutOfFrame(){
        if(x<-100){
            return true;
        }

        return false;

    }
}
