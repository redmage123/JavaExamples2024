import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HogwartsHousePoints {
    public static void main(String[] args) {
        HousePointsManager manager = new HousePointsManager();

        // Create threads for different professors
        Thread dumbledore = new Thread(new Professor("Dumbledore", manager));
        Thread mcgonagall = new Thread(new Professor("McGonagall", manager));
        Thread snape = new Thread(new Professor("Snape", manager));
        Thread flitwick = new Thread(new Professor("Flitwick", manager));

        // Start the threads
        dumbledore.start();
        mcgonagall.start();
        snape.start();
        flitwick.start();

        try {
            // Wait for all threads to complete
            dumbledore.join();
            mcgonagall.join();
            snape.join();
            flitwick.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display final house points
        manager.displayHousePoints();
    }
}

class HousePointsManager {
    private Map<String, Integer> housePoints = new HashMap<>();

    public HousePointsManager() {
        housePoints.put("Gryffindor", 0);
        housePoints.put("Hufflepuff", 0);
        housePoints.put("Ravenclaw", 0);
        housePoints.put("Slytherin", 0);
    }

    public synchronized void awardPoints(String house, int points) {
        int currentPoints = housePoints.get(house);
        housePoints.put(house, currentPoints + points);
    }

    public synchronized void deductPoints(String house, int points) {
        int currentPoints = housePoints.get(house);
        housePoints.put(house, Math.max(0, currentPoints - points));
    }

    public synchronized void displayHousePoints() {
        System.out.println("\nFinal House Points:");
        for (Map.Entry<String, Integer> entry : housePoints.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class Professor implements Runnable {
    private String name;
    private HousePointsManager manager;
    private Random random = new Random();

    public Professor(String name, HousePointsManager manager) {
        this.name = name;
        this.manager = manager;
    }

    @Override
    public void run() {
        String[] houses = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};
        for (int i = 0; i < 10; i++) {
            String house = houses[random.nextInt(houses.length)];
            int points = random.nextInt(50) + 1;
            boolean award = random.nextBoolean();

            if (award) {
                manager.awardPoints(house, points);
                System.out.println(name + " awarded " + points + " points to " + house);
            } else {
                manager.deductPoints(house, points);
                System.out.println(name + " deducted " + points + " points from " + house);
            }

            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}