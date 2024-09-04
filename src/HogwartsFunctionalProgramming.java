import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MagicalItem {
    private String name;
    private String type;
    private int power;

    public MagicalItem(String name, String type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getPower() { return power; }

    @Override
    public String toString() {
        return name + " (" + type + ", power: " + power + ")";
    }
}

public class HogwartsFunctionalProgramming {
    public static void main(String[] args) {
        List<MagicalItem> magicalItems = Arrays.asList(
            new MagicalItem("Expelliarmus", "Spell", 5),
            new MagicalItem("Accio", "Spell", 3),
            new MagicalItem("Stupefy", "Spell", 6),
            new MagicalItem("Polyjuice Potion", "Potion", 7),
            new MagicalItem("Felix Felicis", "Potion", 9),
            new MagicalItem("Wolfsbane Potion", "Potion", 8)
        );

        System.out.println("All magical items:");
        magicalItems.forEach(System.out::println);

        // Filter spells
        List<MagicalItem> spells = magicalItems.stream()
            .filter(item -> item.getType().equals("Spell"))
            .collect(Collectors.toList());

        System.out.println("\nSpells only:");
        spells.forEach(System.out::println);

        // Map to get names of potions
        List<String> potionNames = magicalItems.stream()
            .filter(item -> item.getType().equals("Potion"))
            .map(MagicalItem::getName)
            .collect(Collectors.toList());

        System.out.println("\nPotion names:");
        potionNames.forEach(System.out::println);

        // Find the most powerful magical item
        MagicalItem mostPowerful = magicalItems.stream()
            .max((a, b) -> Integer.compare(a.getPower(), b.getPower()))
            .orElse(null);

        System.out.println("\nMost powerful magical item: " + mostPowerful);

        // Group items by type
        Map<String, List<MagicalItem>> itemsByType = magicalItems.stream()
            .collect(Collectors.groupingBy(MagicalItem::getType));

        System.out.println("\nItems grouped by type:");
        itemsByType.forEach((type, items) -> {
            System.out.println(type + ":");
            items.forEach(item -> System.out.println("  " + item));
        });

        // Calculate average power of all items
        double averagePower = magicalItems.stream()
            .mapToInt(MagicalItem::getPower)
            .average()
            .orElse(0.0);

        System.out.println("\nAverage power of all items: " + averagePower);

        // Higher-order function example
        System.out.println("\nPowerful items (power > 5):");
        filterAndPrint(magicalItems, item -> item.getPower() > 5);
    }

    // Higher-order function
    private static void filterAndPrint(List<MagicalItem> items, java.util.function.Predicate<MagicalItem> predicate) {
        items.stream()
            .filter(predicate)
            .forEach(System.out::println);
    }
}
