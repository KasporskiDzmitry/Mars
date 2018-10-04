package by.dz.mars.entity;

import java.io.Serializable;

/**
 * Created by User on 03.10.2018.
 */
public class Rover implements Serializable {
    private int startSpeed = 1;
    private int speed;
    private int position;

    public Rover() {
        this.position = 0;
        this.speed = startSpeed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void acceleration() {
        this.position += this.speed;
        this.speed *= 2;
    }

    public void reverse() {
        this.startSpeed = -this.startSpeed;
        this.speed = this.startSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rover rover = (Rover) o;

        if (speed != rover.speed) return false;
        return position == rover.position;
    }

    @Override
    public int hashCode() {
        int result = speed;
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        return "Rover{" +
                "speed=" + speed +
                ", position=" + position +
                '}';
    }
}
