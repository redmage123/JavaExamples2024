java.util.ArrayList;
import java.util.List;

// Generic magical container class
class MagicalContainer<T> {
    private List<T> items;

    public MagicalContainer() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public T removeItem() {
        if (items.isEmpty()) {
            return null;
        }
        return items.remove(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<T> getItems() {
        return new ArrayList<>(items);
    }
}

// Hogwarts-specific classes
class MagicalItem {
    private String name;

    public MagicalItem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Wand extends MagicalItem {
    private String core;

    public Wand(String wood, String core) {
        super(wood + " wand");
        this.core = core;
    }

    @Override
    public String toString() {
        return super.toString() + " with " + core + " core";
    }
}

class Potion extends MagicalItem {
    public Potion(String name) {
        super(name + " potion");
    }
}

// Utility class to demonstrate wildcard usage
class HogwartsMagicalInventory {
    // Method using wildcard with upper bound
    public static void displayMagicalItems(MagicalContainer<? extends MagicalItem> container) {
        System.out.println("Magical Items:");
        for (MagicalItem item : container.getItems()) {
            System.out.println("- " + item);
        }
    }

    // Method using wildcard with lower bound
    public static void addWand(MagicalContainer<? super Wand> container, String wood, String core) {
        container.addItem(new Wand(wood, core));
    }
}

// Main class to demonstrate the use of generics and wildcards
public class HogwartsGenericsExample2 {
    public static void main(String[] args) {
        // Create magical containers
        MagicalContainer<Wand> wandContainer = new MagicalContainer<>();
        MagicalContainer<Potion> potionContainer = new MagicalContainer<>();
        MagicalContainer<MagicalItem> generalContainer = new MagicalContainer<>();

        // Add items to containers
        wandContainer.addItem(new Wand("Holly", "Phoenix feather"));
        wandContainer.addItem(new Wand("Vine", "Dragon heartstring"));
        potionContainer.addItem(new Potion("Polyjuice"));
        potionContainer.addItem(new Potion("Felix Felicis"));

        // Demonstrate wildcard with upper bound
        HogwartsMagicalInventory.displayMagicalItems(wandContainer);
        HogwartsMagicalInventory.displayMagicalItems(potionContainer);

        // Demonstrate wildcard with lower bound
        HogwartsMagicalInventory.addWand(wandContainer, "Elder", "Thestral tail hair");
        HogwartsMagicalInventory.addWand(generalContainer, "Yew", "Phoenix feather");

        // Display the updated wand container
        System.out.println("\nUpdated Wand Container:");
        HogwartsMagicalInventory.displayMagicalItems(wandContainer);

        // Display the general container
        System.out.println("\nGeneral Container:");
        HogwartsMagicalInventory.displayMagicalItems(generalContainer);
    }