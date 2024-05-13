package pl.edu.mimuw;

import java.util.Random;

public class Losowanie{
    public static int losuj(int bottom, int top){
        Random rand = new Random();
        return rand.nextInt((top - bottom) + 1) + bottom;
    }
}
