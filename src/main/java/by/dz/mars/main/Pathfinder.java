package by.dz.mars.main;

import by.dz.mars.entity.Rover;

/**
 * Created by User on 03.10.2018.
 */
public class Pathfinder {
    private Rover rover;
    private String bestInstruction;
    private String possibleBestInstruction;

    public Pathfinder(Rover rover) {
        this.rover = rover;
        this.bestInstruction = "";
        this.possibleBestInstruction = "";
    }

    public String findBestInstruction(int position) {
        findPossibleBestInstruction(position);

        rover.reset();

        findBestInstruction(position, possibleBestInstruction.length());

        return bestInstruction;
    }

    private void findPossibleBestInstruction(int position) {
        int closest;
        while (true) {
            int nextPos;

            while (true) {
                doAcc();

                nextPos = rover.getPosition() + rover.getSpeed();

                if (rover.getSpeed() > 0) {
                    if (nextPos >= position) {
                        if (position - rover.getPosition() <= nextPos - position) {
                            closest = rover.getPosition();
                            break;
                        } else {
                            doAcc();
                            closest = rover.getPosition();
                            break;
                        }
                    }
                } else {
                    if (nextPos <= position) {
                        if (rover.getPosition() - position <= position - nextPos) {
                            closest = rover.getPosition();
                            break;
                        } else {
                            doAcc();
                            closest = rover.getPosition();
                            break;
                        }
                    }
                }
            }

            if (closest == position) {
                break;
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



    private void findBestInstruction(int position, int limit) {
        Object[] chars = { 'A', 'R'};

        PermuteCallback callback = new PermuteCallback() {
            String currentInstruction = "";
            @Override
            public void handle(Object[] snapshot) {
                for(int i = 0; i < snapshot.length; i++){
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
                    if (bestInstruction.length() == 0) {
                        bestInstruction = currentInstruction;
                    } else if (currentInstruction.length() <= bestInstruction.length()) {
                        bestInstruction = currentInstruction;
                    }
                }

                currentInstruction = "";
                rover.reset();
            }
        };

        for (int i = 1; i <= limit; i++) {
            permute(chars, i, callback);
        }
    }

    private void permute(Object[] a, int k, PermuteCallback callback) {
        int n = a.length;

        int[] indexes = new int[k];
        int total = (int) Math.pow(n, k);

        Object[] snapshot = new Object[k];
        while (total-- > 0) {
            for (int i = 0; i < k; i++){
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

    private static interface PermuteCallback{
        public void handle(Object[] snapshot);
    };

    private void doAcc() {
        rover.acceleration();
        possibleBestInstruction += "A";
    }

    private void doRev() {
        rover.reverse();
        possibleBestInstruction += "R";
    }
}
