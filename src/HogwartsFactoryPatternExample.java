// Abstract base class for magical items
abstract class MagicalItem {
    protected String name;

    public MagicalItem(String name) {
        this.name = name;
    }

    public abstract void use();
}

// Concrete classes for different types of magical items
class Wand extends MagicalItem {
    private String core;

    public Wand(String name, String core) {
        super(name);
        this.core = core;
    }

    @Override
    public void use() {
        System.out.println("Casting a spell with " + name + ", a wand with " + core + " core.");
    }
}

class Potion extends MagicalItem {
    private String effect;

    public Potion(String name, String effect) {
        super(name);
        this.effect = effect;
    }

    @Override
    public void use() {
        System.out.println("Drinking " + name + " potion. Effect: " + effect);
    }
}

class MagicalCreature extends MagicalItem {
    private String species;

    public MagicalCreature(String name, String species) {
        super(name);
        this.species = species;
    }

    @Override
    public void use() {
        System.out.println("Interacting with " + name + ", a " + species + ".");
    }
}

// Factory class for creating magical items
class MagicalItemFactory {
    public static MagicalItem createMagicalItem(String type, String name, String attribute) {
        switch (type.toLowerCase()) {
            case "wand":
                return new Wand(name, attribute);
            case "potion":
                return new Potion(name, attribute);
            case "creature":
                return new MagicalCreature(name, attribute);
            default:
                throw new IllegalArgumentException("Unknown magical item type: " + type);
        }
    }
}

// Main class to demonstrate the Factory Pattern
public class HogwartsFactoryPatternExample {
    public static void main(String[] args) {
        // Using the factory to create different types of magical items
        MagicalItem wand = MagicalItemFactory.createMagicalItem("wand", "Elder Wand", "Thestral hair");
        MagicalItem potion = MagicalItemFactory.createMagicalItem("potion", "Felix Felicis", "Liquid luck");
        MagicalItem creature = MagicalItemFactory.createMagicalItem("creature", "Buckbeak", "Hippogriff");

        // Using the created magical items
        wand.use();
        potion.use();
        creature.use();

        // Trying to create an invalid magical item type
        try {
            MagicalItem invalidItem = MagicalItemFactory.createMagicalItem("broom", "Firebolt", "Racing broom");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}