package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;


public class GameBackGround {
    //Background Image
    private BufferedImage bgImg;

    //constructor
    public GameBackGround(){
        bgImg = GameUtil.loadBufferedImage(Constant.BG_IMG);
    }

    //  draw image
    public void draw(Graphics g){

        //fill background color
        g.setColor(Constant.BG_COLOR);
        g.fillRect(0,0,Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        g.setColor(Color.black);

        // get size of the image
        int height = bgImg.getHeight();
        int width = bgImg.getWidth();

        // number of image needed
        int count = Constant.FRAME_WIDTH/width+1;

        for(int i=0;i<count;i++){
            g.drawImage(bgImg,width*i,Constant.FRAME_HEIGHT-height,null);
        }
    }
}
