package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird {

    private Rectangle rect;

//    private int acceleration;

    public boolean life = true;

    //store bird's image
    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT = 3;
    //bird status
    private int state;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_UP = 1;
    public static final int STATE_DOWN = 2;

    //bird's position
    private int x=200,y=200;


    private boolean up=false;

    // bird's speed
    private int speed = 4;

    //constructor
    public Bird(){
        images = new BufferedImage[BIRD_IMG_COUNT];
        // initialize bird images
        for(int i=0;i< BIRD_IMG_COUNT;i++){
            images[i] = GameUtil.loadBufferedImage(Constant.BIRD_IMG[i]);
        }

        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rect = new Rectangle(w,h);
    }


    //draw bird
    public void draw(Graphics g){
        flyLogic();

        g.drawImage(images[state],x,y,null);

//        g.drawRect(x,y,rect.width,rect.height);
        rect.x=this.x;
        rect.y=this.y;
    }

    //control direction of bird
    public void flyLogic(){
        if(up){
//            acceleration --;
            y-=speed;
//            if(acceleration < -10){
//                acceleration = -10;
//            }
            if(y<20){
                y=20;
//                acceleration = 0;
            }
        }
        if(!up){
//            acceleration++;
//            y+= acceleration;
//            if(acceleration > 10){
//                acceleration = 10;
//            }
            y+=speed;
            if(y>475){
                y=475;
//                acceleration = 0;
            }
        }
    }

    public void fly(int fly){
        switch(fly){
            case 1:
                state=1;
                up=true;
                break;
            case 2:
                state=2;
                up=false;
                break;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void restartDraw(){
        life = true;
        x=200;
        y=200;
    }
}
