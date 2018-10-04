package by.dz.mars.main;

import by.dz.mars.entity.Rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by User on 03.10.2018.
 */
public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Rover rover = new Rover();

        System.out.println("Enter the number of block:");

        try {;
            System.out.println("Need moves: " + RoverAlgorithm.numberOfMoves(Integer.parseInt(reader.readLine()), rover));
            System.out.println(RoverAlgorithm.moves);
        } catch (IOException e) {
            System.out.println("Error while reading from console");
        } catch (NumberFormatException e) {
            System.out.println("Please, enter a number!");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error while closing the stream");
            }
        }
    }
}
