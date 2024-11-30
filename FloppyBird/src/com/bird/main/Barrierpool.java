package com.bird.main;


import java.util.ArrayList;
import java.util.List;


public class Barrierpool {

    private static List<Barrier> pool = new ArrayList<>();

    public static final int initCount = 16;

    public static final int maxCOunt = 20;

    static {
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }



    public static Barrier getPool(){
        int size = pool.size();

        if (size > 0) {
            //remove and return barrier object
            System.out.println("removed from pool");
            return pool.remove(size-1);
        }else {
            System.out.println("new Barrier");
            return new Barrier();
        }
    }



    public static void setPool(Barrier barrier){
        if (pool.size() < maxCOunt) {
            pool.add(barrier);
            System.out.println("barrier returned");
        }
    }

}