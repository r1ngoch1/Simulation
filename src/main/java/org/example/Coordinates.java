package org.example;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Coordinates)) {
            return false;
        }
        Coordinates other = (Coordinates) obj;
        return (this.x == other.x) && (this.y == other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
