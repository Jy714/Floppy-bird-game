package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Barrier {

    private Rectangle rect;

    private boolean mob = true;

 private int speed = 3;

    private static BufferedImage[] imgs;


    private boolean visible;


    static {
        final int COUNT = 3;

        imgs = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.BARRIER_IMG[i]);
        }
    }


    private int x, y;

    private int width, height;

    private int type;
    public static final int TYPE_TOP_NORMAL = 0;
    public static final int TYPE_BOTTOM_NORMAL = 2;
//    public static final int TYPE_HOVER_NORMAL = 4;
    public static final int TYPE_MOBILE = 6;


    public static final int BARRIER_WIDTH = imgs[0].getWidth();
    public static final int BARRIER_HEIGHT = imgs[0].getHeight();
    public static final int BARRIER_HEAD_WIDTH = imgs[1].getWidth();
    public static final int BARRIER_HEAD_HEIGHT = imgs[1].getHeight();

    public Barrier() {
        rect = new Rectangle();
    }

    public Barrier(int x, int y, int height, int type) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.type = type;
        this.width = BARRIER_WIDTH;
    }

    //根据不同的类型绘制障碍物
    public void draw(Graphics g) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawNormalTop(g);
                break;
//            case TYPE_HOVER_NORMAL:
//                drawHoverNormal(g);
//                break;
            case TYPE_MOBILE:
                drawMobile(g);
                break;
        }

    }

    //绘制从上向下的障碍物
    private void drawTopNormal(Graphics g) {
        //求出所需要的障碍物的块数
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT + 1;
        //for循坏绘制障碍物
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * BARRIER_HEIGHT, null);
        }

        //绘制头
        int y = height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x-=speed;
        if (x < -50) {
            visible=false;
        }
        rect(g);
    }

    private void drawNormalTop(Graphics g) {

        int count = height / BARRIER_HEIGHT + 1;

        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAME_HEIGHT - i * BARRIER_HEIGHT, null);
        }

        int y = Constant.FRAME_HEIGHT - height;
        g.drawImage(imgs[1], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x-=speed;
        if (x < - 50) {
            visible=false;
        }
        rect(g);
    }

//    private void drawHoverNormal(Graphics g) {
//
//        int count = (height - BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT;
//
//        g.drawImage(imgs[1],x,y,null);
//
//        for (int i = 0; i < count; i++) {
//            g.drawImage(imgs[0], x, y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT, null);
//        }
//        rect(g);
//
//        int yll = y+height-BARRIER_HEAD_HEIGHT;
//        g.drawImage(imgs[2],x,yll, null);
//        x-=speed;
//        if (x < - 50) {
//            visible=false;
//        }
//    }

    private void drawMobile(Graphics g) {

        int count = (height - BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT;

        g.drawImage(imgs[1],x,y,null);

        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT, null);
        }
        rect(g);

        int yll = y+height-BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2],x,yll, null);
        x-=speed;
        if (x < - 50) {
            visible=false;
        }

        if(mob){
            y+=3;
            if(y>=250){
                mob = false;
            }
        }else if (!mob){
            y-=3;
            if(y<=100){
                mob=true;
            }
        }
    }

    public void rect(Graphics g){
        int xl = this.x;
        int yl = this.y;
        int wl = imgs[0].getWidth();
//        g.setColor(Color.blue);
//        g.drawRect(xl,yl,wl,height);
        setRectangle(xl,yl,wl,height);
    }

    public void setRectangle(int x,int y,int width,int height){
        rect.x=x;
        rect.y=y;
        rect.width=width;
        rect.height=height;
    }


    public boolean isInFrame(){
        return 600-x>150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }
}
