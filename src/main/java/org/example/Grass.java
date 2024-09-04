package org.example;

import java.util.ArrayList;
import java.util.List;

public class Grass extends Entity{
    public Grass() {
    }
    public static ArrayList<Entity> generateGrasses(int amount){
        ArrayList<Entity> grasses = new ArrayList<Entity>();
        for (int i = 0;i<amount;i++){
            grasses.add(new Grass());
        }
        return grasses;
    }
}
