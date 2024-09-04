package org.example;

import java.util.ArrayList;

public class Tree extends Entity{
    public static ArrayList<Entity> generateTree(int amount){
        ArrayList<Entity> tree = new ArrayList<Entity>();
        for (int i = 0;i<amount;i++){
            tree.add(new Tree());
        }
        return tree;
    }
}
