package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private HashMap<Coordinates, Entity> locationOfCreatures;
    private HashMap<Coordinates, Entity> reservedLocations; // Резервирование клеток

    public Map() {
        this.locationOfCreatures = new HashMap<>();
        this.reservedLocations = new HashMap<>();
    }

    public HashMap<Coordinates, Entity> getLocationOfCreatures() {
        return locationOfCreatures;
    }

    public void addEntity(Entity entity, Coordinates coordinates) {
        if (isPlaceFree(coordinates)) {
            locationOfCreatures.put(coordinates, entity);
        }
    }

    public void addEntities(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            addEntity(entity, findFreePlace());
        }
    }

    public boolean isPlaceFree(Coordinates coordinates) {
        return !locationOfCreatures.containsKey(coordinates) && !reservedLocations.containsKey(coordinates);
    }

    public void reservePlace(Coordinates coordinates, Entity entity) {
        reservedLocations.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        locationOfCreatures.remove(coordinates);
        reservedLocations.remove(coordinates); // Освобождение резервирования
    }

    public Coordinates findFreePlace() {
        while (true) {
            Coordinates coordinate = Renderer.randomizer(Simulation.n, Simulation.m);
            if (isPlaceFree(coordinate)) {
                return coordinate;
            }
        }
    }

    public Coordinates findEntityCoordinates(Entity entity) {
        for (var entry : locationOfCreatures.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Coordinates findNearestEntity(Entity source, Class<? extends Entity> targetClass) {
        Coordinates sourceCoordinates = findEntityCoordinates(source);
        Coordinates nearestCoordinates = null;
        int minDistance = Integer.MAX_VALUE;

        for (var entry : locationOfCreatures.entrySet()) {
            if (targetClass.isInstance(entry.getValue())) {
                int distance = calculateDistance(sourceCoordinates, entry.getKey());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCoordinates = entry.getKey();
                }
            }
        }
        return nearestCoordinates;
    }

    private int calculateDistance(Coordinates a, Coordinates b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public void finalizeMoves() {
        // Перемещаем зарезервированные клетки в основную карту
        locationOfCreatures.putAll(reservedLocations);
        reservedLocations.clear();
    }
}
