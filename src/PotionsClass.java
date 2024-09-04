import java.util.concurrent.ThreadLocalRandom;

public class PotionsClass {
    public static void main(String[] args) {
        IngredientStore store = new IngredientStore();
        
        Thread harry = new Thread(new Student("Harry", store));
        Thread hermione = new Thread(new Student("Hermione", store));
        Thread ron = new Thread(new Student("Ron", store));
        
        harry.start();
        hermione.start();
        ron.start();
        
        try {
            harry.join();
            hermione.join();
            ron.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Potions class is over!");
    }
}

class IngredientStore {
    private int boomslangSkin = 3;
    private int lacewingFlies = 3;
    
    public void useIngredients(String studentName) {
        System.out.println(studentName + " is attempting to gather ingredients.");
        
        synchronized(this) {
            if (boomslangSkin > 0 && lacewingFlies > 0) {
                try {
                    System.out.println(studentName + " is measuring ingredients.");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
                    
                    boomslangSkin--;
                    lacewingFlies--;
                    
                    System.out.println(studentName + " has successfully gathered ingredients.");
                    System.out.println("Remaining: Boomslang Skin: " + boomslangSkin + 
                                       ", Lacewing Flies: " + lacewingFlies);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(studentName + " couldn't gather ingredients. Not enough left!");
            }
        }
    }
}

class Student implements Runnable {
    private String name;
    private IngredientStore store;
    
    public Student(String name, IngredientStore store) {
        this.name = name;
        this.store = store;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            store.useIngredients(name);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}