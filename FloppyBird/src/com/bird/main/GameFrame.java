package com.bird.main;

import static com.bird.util.Constant.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


/**
 * program frame class. All the drawing of the program will do at here.
 */
public class GameFrame extends Frame  {

    private GameBackGround gameBackGround;

    private Bird bird;

    private GameBarrierLayer gameBarrierLayer;

    private GameLanding gameLanding;

    private BufferedImage buffImg = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);

    //constructor
    public GameFrame(){
        //visibility of frame
        setVisible(true);
        //frame size
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //frame title
        setTitle(FRAME_TITLE);
        //frame init position
        setLocation(FRAME_X,FRAME_Y);
        //set if the frame is resizable
        setResizable(false);

        //close program
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//terminates program
            }
        });

        initGame();

        //start the thread
        new run().start();

        // add a keyboard event listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });

    }

    public void initGame(){
        gameBackGround = new GameBackGround();
        bird = new Bird();
        gameLanding = new GameLanding();
        gameBarrierLayer = new GameBarrierLayer();
    }

    class run extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();
                try{
                    Thread.sleep(30);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void update(Graphics g) {

        if(bird.life){
            Graphics graphics = buffImg.getGraphics();
            gameBackGround.draw(graphics);
            bird.draw(graphics);
            gameLanding.draw(graphics);
            gameBarrierLayer.draw(graphics,bird);

            g.drawImage(buffImg,0,0,null);
        }else{
            String gameOver = "Game over!";
            g.setColor(Color.red);
            g.setFont(new Font("Microsoft Yahei",1,60));
            g.drawString(gameOver,120,250);

            g.setFont(new Font("Microsoft Yahei",1,30));
            String reset = "Press Space to Restart";
            g.drawString(reset,120,300);
        }

    }

    public void add(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if(bird.life == false){
                    restart();
                }
                break;
        }
    }

    public void minu(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                bird.fly(2);
                break;
        }
    }

    public void restart(){
        gameBarrierLayer.restart();
        bird.restartDraw();
    }
}
