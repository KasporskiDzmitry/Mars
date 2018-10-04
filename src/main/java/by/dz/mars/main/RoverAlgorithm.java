package by.dz.mars.main;

import by.dz.mars.entity.Rover;

/**
 * Created by User on 03.10.2018.
 */
public class RoverAlgorithm {

    public static String moves = "";

    public static int numberOfMoves(int position, Rover rover) {
        int numberOfMoves = 0;
        while (rover.getPosition() != position) {
            if (rover.getSpeed() > 0) {
                if (rover.getPosition() > position) {
                    rover.reverse();
                    moves += "R ";
                    numberOfMoves++;
                }
            } else if (rover.getPosition() < position) {
                rover.reverse();
                moves += "R ";
                numberOfMoves++;
            }

            rover.acceleration();
            moves += "A ";
            numberOfMoves++;
        }
        return numberOfMoves;
    }

}
