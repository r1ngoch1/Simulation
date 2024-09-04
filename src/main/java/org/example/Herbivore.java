package org.example;

import java.util.ArrayList;

public class Herbivore extends Creature {
    @Override
    protected Coordinates findNearestFood(Map map) {
        return map.findNearestEntity(this, Grass.class);
    }

    @Override
    public void makeMove(Map map) {
        super.makeMove(map);
        // Eating the grass if it is on the same cell
        Coordinates current = map.findEntityCoordinates(this);
        Entity entity = map.getLocationOfCreatures().get(current);
        if (entity instanceof Grass) {
            map.removeEntity(current);
            this.setHealthPoint(this.getHealthPoint() + 10); // Gain health from eating
        }
    }

    public static ArrayList<Entity> generateHerbivore(int amount) {
        ArrayList<Entity> herbivores = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            herbivores.add(new Herbivore());
        }
        return herbivores;
    }
}
