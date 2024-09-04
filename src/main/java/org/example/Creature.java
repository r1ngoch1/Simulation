package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Creature extends Entity {
    private int speed;
    private int healthPoint;

    public Creature() {
        this.healthPoint = 100; // Начальное значение здоровья по умолчанию
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void makeMove(Map map) {
        Coordinates current = map.findEntityCoordinates(this);
        if (current == null) {
            System.out.println("Текущее местоположение существа не найдено.");
            return;
        }

        Coordinates target = findNearestFood(map);
        if (target == null) {
            System.out.println("Цель для существа не найдена.");
            return;
        }

        System.out.println("Существо движется от " + current.getX() + "," + current.getY() +
                " к " + target.getX() + "," + target.getY());

        moveTowards(target, map);
    }

    protected abstract Coordinates findNearestFood(Map map);

    private void moveTowards(Coordinates target, Map map) {
        Coordinates current = map.findEntityCoordinates(this);
        if (current == null) return;

        int deltaX = target.getX() - current.getX();
        int deltaY = target.getY() - current.getY();

        // Перемещение на 1 единицу в сторону цели
        List<Coordinates> potentialMoves = new ArrayList<>();
        potentialMoves.add(new Coordinates(current.getX() + Integer.signum(deltaX), current.getY()));
        potentialMoves.add(new Coordinates(current.getX(), current.getY() + Integer.signum(deltaY)));
        potentialMoves.add(new Coordinates(current.getX() + Integer.signum(deltaX), current.getY() + Integer.signum(deltaY)));

        for (Coordinates move : potentialMoves) {
            if (map.isPlaceFree(move)) {
                System.out.println("Существо резервирует место на " + move.getX() + "," + move.getY());
                map.reservePlace(move, this);
                map.removeEntity(current);
                return;
            }
        }

        System.out.println("Новое место занято, перемещение невозможно.");
    }


    private Coordinates tryRandomMove(Coordinates current, Map map) {
        List<Coordinates> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinates(current.getX() + 1, current.getY()));
        possibleMoves.add(new Coordinates(current.getX() - 1, current.getY()));
        possibleMoves.add(new Coordinates(current.getX(), current.getY() + 1));
        possibleMoves.add(new Coordinates(current.getX(), current.getY() - 1));

        Collections.shuffle(possibleMoves); // Перемешиваем направления

        for (Coordinates move : possibleMoves) {
            if (map.isPlaceFree(move)) {
                System.out.println("Существо перемещается на " + move.getX() + "," + move.getY() + " случайно.");
                return move;
            }
        }
        return null; // Если нет доступных движений
    }



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
