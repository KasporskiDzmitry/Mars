package by.dz.mars.main;

import by.dz.mars.entity.Rover;

/**
 * Created by User on 03.10.2018.
 */
public class Pathfinder {

    private Rover rover;

    private String commands;
    private int numberOfMoves;



    public Pathfinder(Rover rover) {
        this.rover = rover;
        this.commands = "";
    }


    public int getNumberOfMoves(int position) {
        int closest;
        while (true) {
            closest = findClosest(position);
            if (closest == position) {
                return numberOfMoves;
            } else if (rover.getSpeed() > 0) {
                if (closest > position) {
                    doRev();
                } else {
                    doRev();
                    doRev();
                }
            } else {
                if (closest > position) {
                    doRev();
                    doRev();
                } else {
                    doRev();
                }
            }
        }
    }

    public String getCommands() {
        return this.commands;
    }

    private int findClosest(int position) {
        int nextPos;

        while (true) {
            doAcc();

            nextPos = rover.getPosition() + rover.getSpeed();

            if (rover.getSpeed() > 0) {
                if (nextPos >= position) {
                    if (position - rover.getPosition() <= nextPos - position) {
                        return rover.getPosition();
                    } else {
                        doAcc();
                        return rover.getPosition();
                    }
                }
            } else {
                if (nextPos <= position) {
                    if (rover.getPosition() - position <= position - nextPos) {
                        return rover.getPosition();
                    } else {
                        doAcc();
                        return rover.getPosition();
                    }
                }
            }
        }
    }

    private void doAcc() {
        rover.acceleration();
        commands += "A";
        numberOfMoves++;
    }

    private void doRev() {
        rover.reverse();
        commands += "R";
        numberOfMoves++;
    }
}
