package com.bird.main;

import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game landing page
 */
public class GameLanding {
    // number of cloud
    private static final int CLOUD_COUNT = 2;
    // cloud container
    private List<Cloud> clouds;
    //cloud speed
    private static final int CLOUD_SPEED = 1;
    //image
    private BufferedImage[] img;

    // used to random generate clouds
    private Random random;

    //constructor
    public GameLanding(){
        clouds = new ArrayList<>();
        img = new BufferedImage[CLOUD_COUNT];

        //store img into container
        for(int i=0;i<CLOUD_COUNT;i++){
            img[i] = GameUtil.loadBufferedImage("img/cloud"+i+".png");
        }
        random = new Random();
    }

    public void draw(Graphics g){
        logic();

        for(int i=0;i< clouds.size();i++){
            clouds.get(i).draw(g);

        }
    }

    /**
     * control the number of cloud
     */

    private void logic(){
        if((int)(500*Math.random()) < 5){
            Cloud cloud = new Cloud(img[random.nextInt(CLOUD_COUNT)],CLOUD_SPEED,600,random.nextInt(150));
            clouds.add(cloud);
        }

        for(int i=0;i< clouds.size();i++){
            Cloud cloud = clouds.get(i);

            if(cloud.isOutOfFrame()){
                clouds.remove(i);
                i--;
                System.out.println("Cloud"+i+" has been removed");
            }
        }
    }
}
