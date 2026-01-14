import java.io.*;
import java.util.*;

//The best drone percentage appears to be 47%
public class Driver {
    private static int currentTime = 0;
    private static boolean isTrainCrossing = false;

    public static void main(String[] args) throws Exception {
        // Variable set up
        final double PERCENT_BY_DRONE = 0.25;
        int totalTrucks = (int) Math.ceil((1500 * (1 - PERCENT_BY_DRONE) / 10));
        int totalDrones = (int) (1500 * PERCENT_BY_DRONE);
        int totalDroneTime = ((totalDrones - 1) * 3 + 60);
        int totalTruckTime = 0;
        PriorityQueue<Event> pq = new PriorityQueue<>();
        Queue<TruckAtCrossingEvent> truckQueue = new LinkedList<>();
        ArrayList<String> trainSchedule = new ArrayList<>();
        ArrayList<String> truckTripTimes = new ArrayList<>();

        // Pre simulation actions to populate priority queue with all known values
        populateTrainSchedule(pq);
        populateTruckSchedule(pq, totalTrucks);
        // Main while loop
        // The general skeleton was heavily influenced by advice from Reese
        while (!pq.isEmpty()) {

            Event e = pq.poll();

            if (e instanceof TruckStartEvent) {
                e.execute();
                System.out.println("Truck " + e.getName() + " left at " + currentTime);
                // Create a coressponding truck arrive at crossing event to each truck start
                TruckAtCrossingEvent truckCrossStart = new TruckAtCrossingEvent(currentTime + 100, e.getName(),
                        e.getStartTime());
                pq.add(truckCrossStart);

            } else if (e instanceof TrainStartEvent) {
                e.execute();
                String temp = "Train " + e.getName() + " arrived at " + currentTime;
                System.out.println(temp);
                trainSchedule.add(temp);

            } else if (e instanceof TrainStopEvent) {
                e.execute();
                String temp = "Train " + e.getName() + " left at " + currentTime;
                System.out.println(temp);
                trainSchedule.add(temp);
                int i = 1;

                // Once train has left, begin unloading queue of waiting trucks
                while (!truckQueue.isEmpty()) {
                    TruckPassCrossingEvent truckPass = new TruckPassCrossingEvent(currentTime + i,
                            truckQueue.poll().getName(), e.getStartTime());
                    pq.add(truckPass);
                    i++;
                }

            } else if (e instanceof TruckAtCrossingEvent) {
                e.execute();
                System.out.println("Truck " + e.getName() + " arrived at crossing at " + currentTime);

                // Nested if statement to check if the base case, an empty queue and no train is
                // met
                if (!getIsTrainCrossing() && truckQueue.isEmpty()) {
                    TruckPassCrossingEvent truckPass = new TruckPassCrossingEvent(currentTime, e.getName(),
                            e.getStartTime());
                    pq.add(truckPass);

                } else {
                    // Otherwise add to queue
                    truckQueue.offer((TruckAtCrossingEvent) e);
                }
            }

            else if (e instanceof TruckPassCrossingEvent) {
                e.execute();
                System.out.println("Truck " + e.getName() + " passed crossing at " + currentTime);
                // Finalize truck end considering remaining path is unimpeded
                TruckEndEvent truckEnd = new TruckEndEvent(currentTime + 900, e.getName(), e.getStartTime());
                pq.add(truckEnd);

            } else if (e instanceof TruckEndEvent) {
                e.execute();
                System.out.println("Truck " + e.getName() + " finished its route at " + currentTime);
                String temp = "Truck " + e.getName() + "'s route took " + (currentTime - e.getStartTime()) + " minutes";
                truckTripTimes.add(temp);
                totalTruckTime += (currentTime - e.getStartTime());

            }
        }
        // Final stats
        System.out.println();
        System.out.println("Train scheudle: ");
        for (int i = 0; i < trainSchedule.size(); i++) {
            System.out.println(trainSchedule.get(i));
        }
        System.out.println("Individual truck route times: ");
        for (int i = 0; i < truckTripTimes.size(); i++) {
            System.out.println(truckTripTimes.get(i));
        }
        System.out.println(totalTrucks + " trucks and " + totalDrones + " drones.");
        System.out.println("Truck total time: " + currentTime);
        System.out.println("Truck average time: " + (totalTruckTime / totalTrucks));
        System.out.println("Drone trip time: 60 minutes");
        System.out.println("Drone total time: " + totalDroneTime);
        System.out.println("Total time: " + Math.max(currentTime, totalDroneTime));
    }

    /**
     * Method that takes the train schedule and creates start / stop events
     * accordingly
     * 
     * @param pq the queue to be appended
     * @throws FileNotFoundException
     */
    public static void populateTrainSchedule(PriorityQueue<Event> pq) throws FileNotFoundException {
        FileInputStream fileStream = new FileInputStream("CS1181 Project 3\\src\\train_schedule.txt");
        Scanner fileReader = new Scanner(fileStream);
        int i = 1;
        // Add each train to the schedule
        while (fileReader.hasNextLine()) {
            int crossTime = fileReader.nextInt();
            int duration = fileReader.nextInt();
            TrainStartEvent temp = new TrainStartEvent(crossTime, i, crossTime);
            TrainStopEvent temp2 = new TrainStopEvent(crossTime + duration, i, crossTime);
            pq.add(temp);
            pq.add(temp2);
            i++;

        }
        fileReader.close();
    }

    /**
     * Method that creates a truck start event at start time and every 15 minutes
     * after
     * 
     * @param pq          queue to be appended
     * @param totalTrucks total number of trucks to account for
     */
    public static void populateTruckSchedule(PriorityQueue<Event> pq, int totalTrucks) {
        int startTime = 0;
        // Add each truck start to the schedule
        for (int i = 0; i < totalTrucks; i++) {
            TruckStartEvent temp = new TruckStartEvent(startTime, i + 1, startTime);
            pq.add(temp);
            startTime += 15;
        }
    }

    public static void setCurrentTime(int newTime) {
        currentTime = newTime;
    }

    public static int getCurrentTime() {
        return currentTime;
    }

    public static void setIsTrainCrossing(boolean b) {
        isTrainCrossing = b;
    }

    public static boolean getIsTrainCrossing() {
        return isTrainCrossing;
    }

}
