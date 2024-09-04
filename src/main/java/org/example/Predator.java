package org.example;

import java.util.ArrayList;

public class Predator extends Creature {
    @Override
    protected Coordinates findNearestFood(Map map) {
        return map.findNearestEntity(this, Herbivore.class);
    }

    @Override
    public void makeMove(Map map) {
        super.makeMove(map);
        // Eating the herbivore if it is on the same cell
        Coordinates current = map.findEntityCoordinates(this);
        Entity entity = map.getLocationOfCreatures().get(current);
        if (entity instanceof Herbivore) {
            map.removeEntity(current);
            this.setHealthPoint(this.getHealthPoint() + 20); // Gain health from eating
        }
    }

    public static ArrayList<Entity> generatePredator(int amount) {
        ArrayList<Entity> predators = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            predators.add(new Predator());
        }
        return predators;
    }
}
