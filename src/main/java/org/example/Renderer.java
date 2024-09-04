package org.example;

import java.util.HashMap;
import java.util.Set;

public class Renderer {
    private int n; // размер симуляции в ширину
    private int m; // и в длину



    public void drawMap(Map map){
        Set<Coordinates> coordinates = map.getLocationOfCreatures().keySet();
        for(int i=0;i<n;i++){
            for (int j = 0;j<m;j++){
                if (!coordinates.contains(new Coordinates(i,j)) ){
                    System.out.print(" "+"\u2610" + " ");
                }else {
                    System.out.print(" "+spriteForSquare(map.getLocationOfCreatures().get(new Coordinates(i,j))) + " ");

                }
            }
            System.out.println("");
        }

    }
    public void entityRenderer(){


    }


    public Renderer(int n, int m) {
        this.n = n;
        this.m = m;
    }


    public static Coordinates randomizer(int x,int y){
        return new Coordinates((int) (Math.random()*x), (int) (Math.random()*y));
    }
    private String spriteForSquare(Entity entity){
        switch (entity.getClass().getSimpleName()){
            case ("Herbivore"):
                return "\uD83D\uDE08";
            case ("Grass"):
                return "\uD83D\uDE07";
            case ("Tree"):
                return "\uD83D\uDE05";
            case ("Rock"):
                return "\uD83D\uDE02";
            case ("Predator"):
                return "\uD83D\uDE03";
            default: return "0";

        }


    }


}
