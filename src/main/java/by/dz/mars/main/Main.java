package by.dz.mars.main;

import by.dz.mars.entity.Rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 03.10.2018.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pathfinder pathfinder = new Pathfinder(new Rover());
        String bestInstruction;

        System.out.println("Enter the number of block:");

        try {
            bestInstruction = pathfinder.findBestInstruction(Integer.parseInt(reader.readLine()));
            System.out.println("Best instruction is: " + bestInstruction);
            System.out.println("Need moves: " + bestInstruction.length());
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
