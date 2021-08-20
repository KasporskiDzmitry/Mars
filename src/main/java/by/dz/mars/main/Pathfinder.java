package by.dz.mars.main;

import by.dz.mars.entity.Rover;

/**
 * Created by User on 03.10.2018.
 */
public class Pathfinder {
    private Rover rover;
    private final String[] commands = {"A", "R"};
    private String bestInstruction;

    public Pathfinder(Rover rover) {
        this.rover = rover;
        this.bestInstruction = "";
    }

    public String findBestInstruction(int position) {
        PermuteCallback callback = new PermuteCallback() {
            String currentInstruction = "";

            @Override
            public void handle(String[] snapshot) {
                for (int i = 0; i < snapshot.length; i++) {
                    currentInstruction += snapshot[i];
                }

                // checking current instruction
                for (int j = 0; j < currentInstruction.length(); j++) {
                    if (currentInstruction.charAt(j) == 'A') {
                        rover.acceleration();
                    } else {
                        rover.reverse();
                    }
                }
                if (rover.getPosition() == position) {
                    bestInstruction = currentInstruction;
                }

                currentInstruction = "";
                rover.reset();
            }
        };

        int i = 0;

        while (true) {
            permute(commands, i++, callback);
            if (bestInstruction.length() > 0) {
                return bestInstruction;
            }
        }
    }

    private void permute(String[] a, int k, PermuteCallback callback) {
        int n = a.length;

        int[] indexes = new int[k];
        int total = (int) Math.pow(n, k);

        String[] snapshot = new String[k];
        while (total-- > 0) {
            for (int i = 0; i < k; i++) {
                snapshot[i] = a[indexes[i]];
            }
            callback.handle(snapshot);

            for (int i = 0; i < k; i++) {
                if (indexes[i] >= n - 1) {
                    indexes[i] = 0;
                } else {
                    indexes[i]++;
                    break;
                }
            }
        }
    }

    private static interface PermuteCallback {
        public void handle(String[] snapshot);
    }
}
