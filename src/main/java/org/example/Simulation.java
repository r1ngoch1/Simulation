package org.example;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static final int n = 10;
    public static final int m = 10;

    public static void main(String[] args) {
        Map map = new Map();
        Renderer renderer = new Renderer(n, m);
        map.addEntities(Grass.generateGrasses(5));
        map.addEntities(Herbivore.generateHerbivore(3));
        map.addEntities(Tree.generateTree(2));
        map.addEntities(Rock.generateRock(2));
        map.addEntities(Predator.generatePredator(2));

        for (int i = 0; i < 20; i++) {  // 20 cycles of simulation
            System.out.println("Cycle " + (i + 1));

            // Создаем список для всех существ, чтобы избежать ConcurrentModificationException
            List<Entity> creatures = new ArrayList<>(map.getLocationOfCreatures().values());
            List<Coordinates> toRemove = new ArrayList<>();  // List to store entities to remove

            for (Entity entity : creatures) {
                if (entity instanceof Creature) {
                    ((Creature) entity).makeMove(map);

                    // После движения проверяем, нужно ли удалить сущность (например, если она съедена)
                    Coordinates current = map.findEntityCoordinates(entity);
                    if (entity instanceof Predator && map.getLocationOfCreatures().get(current) instanceof Herbivore) {
                        toRemove.add(current);  // Добавляем местоположение травоядного для удаления
                    } else if (entity instanceof Herbivore && map.getLocationOfCreatures().get(current) instanceof Grass) {
                        toRemove.add(current);  // Добавляем местоположение травы для удаления
                    }
                }
            }

            // Удаляем сущности после итерации
            for (Coordinates coord : toRemove) {
                map.removeEntity(coord);
            }
            for (Coordinates coord : map.getLocationOfCreatures().keySet()) {
                Entity entity = map.getLocationOfCreatures().get(coord);
                System.out.println("Существо " + entity.getClass().getSimpleName() + " на позиции " + coord.getX() + "," + coord.getY());
            }


            renderer.drawMap(map);
            System.out.println("");
        }
    }
}
