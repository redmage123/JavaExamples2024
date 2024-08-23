import java.util.ArrayList;
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
}

// Hogwarts-specific classes
class Wand {
    private String wood;
    private String core;

    public Wand(String wood, String core) {
        this.wood = wood;
        this.core = core;
    }

    @Override
    public String toString() {
        return wood + " wand with " + core + " core";
    }
}

class Potion {
    private String name;

    public Potion(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " potion";
    }
}

// Main class to demonstrate the use of generics
public class HogwartsGenericsExample {
    public static void main(String[] args) {
        // Create a magical container for wands
        MagicalContainer<Wand> wandContainer = new MagicalContainer<>();
        wandContainer.addItem(new Wand("Holly", "Phoenix feather"));
        wandContainer.addItem(new Wand("Vine", "Dragon heartstring"));

        // Create a magical container for potions
        MagicalContainer<Potion> potionContainer = new MagicalContainer<>();
        potionContainer.addItem(new Potion("Polyjuice"));
        potionContainer.addItem(new Potion("Felix Felicis"));

        // Demonstrate using the containers
        System.out.println("Removing a wand: " + wandContainer.removeItem());
        System.out.println("Removing a potion: " + potionContainer.removeItem());

        // Show that the containers are type-safe
        // Uncommenting the following line would result in a compile-time error:
        // wandContainer.addItem(new Potion("Amortentia"));
    }
}