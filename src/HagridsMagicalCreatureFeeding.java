import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class HagridsMagicalCreatureFeeding {
    public static void main(String[] args) {
        FeedingArea feedingArea = new FeedingArea();
        
        Thread hagrid = new Thread(new Hagrid(feedingArea), "Hagrid");
        Thread niffler = new Thread(new MagicalCreature(feedingArea, "Niffler"), "Niffler");
        Thread hippogriff = new Thread(new MagicalCreature(feedingArea, "Hippogriff"), "Hippogriff");
        Thread thestral = new Thread(new MagicalCreature(feedingArea, "Thestral"), "Thestral");
        
        hagrid.start();
        niffler.start();
        hippogriff.start();
        thestral.start();
        
        try {
            Thread.sleep(20000);  // Let the feeding continue for 20 seconds
            feedingArea.setFeedingTime(false);
            hagrid.join();
            niffler.join();
            hippogriff.join();
            thestral.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Feeding time is over!");
    }
}

class FeedingArea {
    private Queue<String> food = new LinkedList<>();
    private final int MAX_FOOD = 3;
    private boolean feedingTime = true;
    
    public synchronized void addFood(String item) throws InterruptedException {
        while (food.size() == MAX_FOOD) {
            System.out.println("Feeding area is full. Hagrid waits.");
            wait();  // Hagrid waits if the feeding area is full
        }
        food.add(item);
        System.out.println("Hagrid added " + item + " to the feeding area.");
        notifyAll();  // Notify all waiting creatures that food is available
    }
    
    public synchronized String takeFood(String creature) throws InterruptedException {
        while (food.isEmpty() && feedingTime) {
            System.out.println(creature + " is waiting for food.");
            wait();  // Creatures wait if there's no food
        }
        if (!feedingTime && food.isEmpty()) {
            return null;  // End of feeding time and no food left
        }
        String item = food.poll();
        System.out.println(creature + " took " + item + " from the feeding area.");
        notify();  // Notify Hagrid that there's space in the feeding area
        return item;
    }
    
    public synchronized void setFeedingTime(boolean feedingTime) {
        this.feedingTime = feedingTime;
        notifyAll();  // Notify all waiting threads that feeding time has changed
    }
    
    public boolean isFeedingTime() {
        return feedingTime;
    }
}

class Hagrid implements Runnable {
    private FeedingArea feedingArea;
    private Random random = new Random();
    private String[] foodItems = {"Ferret", "Dead Rabbit", "Chicken", "Rat"};
    
    public Hagrid(FeedingArea feedingArea) {
        this.feedingArea = feedingArea;
    }
    
    @Override
    public void run() {
        try {
            while (feedingArea.isFeedingTime()) {
                String food = foodItems[random.nextInt(foodItems.length)];
                feedingArea.addFood(food);
                Thread.sleep(random.nextInt(1000, 3000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class MagicalCreature implements Runnable {
    private FeedingArea feedingArea;
    private String name;
    private Random random = new Random();
    
    public MagicalCreature(FeedingArea feedingArea, String name) {
        this.feedingArea = feedingArea;
        this.name = name;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                String food = feedingArea.takeFood(name);
                if (food == null) break;  // End of feeding time
                Thread.sleep(random.nextInt(2000, 5000));  // Time to eat
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}