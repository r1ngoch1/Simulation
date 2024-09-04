package org.example;

import java.util.ArrayList;

public class Rock extends Entity{
    public static ArrayList<Entity> generateRock(int amount){
        ArrayList<Entity> rock = new ArrayList<Entity>();
        for (int i = 0;i<amount;i++){
            rock.add(new Rock());
        }
        return rock;
    }
}
