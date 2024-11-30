package com.bird.main;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBarrierLayer {

    private GameTime gameTime;
    private Random random = new Random();
    private List<Barrier> barriers;
    private int txt;

    public GameBarrierLayer(){
        barriers = new ArrayList<>();
        gameTime = new GameTime();
    }


    public void draw(Graphics g,Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);

            if (barrier.isVisible()) {
                barrier.draw(g);
            }else {
                Barrier remove = barriers.remove(i);
                Barrierpool.setPool(remove);
                i--;
            }
        }
        collideBird(bird);
        logic(g);

    }

    public void logic(Graphics g){
        if (barriers.size() == 0) {
            ran();
            gameTime.begin();
            insert(600,0,numberTop,0);
            insert(600,500-numberDown,numberDown,2);
        }else {
            long differ = gameTime.differ();
            g.setColor(Color.white);
            g.setFont(new Font("Microsoft Yahei",1,20));
            g.drawString("Time: "+ differ+"s",30,50);

            txt= getTxt();

            if(differ <= txt){
                g.drawString("Highest Record: "+txt,200,50);
            }else{
                setTxt(String.valueOf(differ));
                g.drawString("Highest Record: "+differ,200,50);
            }

            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                ran();
                if (number > 450){
                    insert(600,125,200,6);
                }else{
                    insert(600,0,numberTop,0);
                    insert(600,500-numberDown,numberDown,2);
                }

            }
        }
    }

    File file = new File("C:\\Users\\EndUser\\Desktop\\Computing\\back end(Java)\\FloppyBird\\FloppyBird\\FloppyBird\\Record.txt");

    public int getTxt() {
        int read = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            read = Integer.parseInt(in.readLine());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return read;
    }

    public void setTxt(String str) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insert(int x,int y ,int num,int type){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }

    private int numberTop;

    private int numberDown;
    private int number;

    public void ran(){
        numberTop = random.nextInt(400)+100;
        numberDown = random.nextInt(400)+100;
        number = random.nextInt(500);
        //如果管道重合，则重新随机
        if (numberTop + numberDown > 350) {
            ran();
        }
    }
    
    public boolean collideBird(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);

            if(barrier.getRect().intersects(bird.getRect())){
                System.out.println("Collision occured");
                bird.life = false;
            }
        }
        return false;
    }

    public void restart(){
        barriers.clear();
    }

}
