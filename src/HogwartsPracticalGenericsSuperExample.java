import java.util.ArrayList;
import java.util.List;

// Base class for spells
class Spell {
    protected String name;
    protected int power;

    public Spell(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public void cast() {
        System.out.println("Casting " + name + " with power " + power);
    }

    @Override
    public String toString() {
        return name;
    }
}

// Subclasses for different types of spells
class CharmSpell extends Spell {
    public CharmSpell(String name, int power) {
        super(name, power);
    }
}

class TransfigurationSpell extends Spell {
    public TransfigurationSpell(String name, int power) {
        super(name, power);
    }
}

// Generic spell book class
class SpellBook<T extends Spell> {
    private List<T> spells = new ArrayList<>();

    public void addSpell(T spell) {
        spells.add(spell);
    }

    public List<T> getSpells() {
        return spells;
    }
}

// Wizard class that can use spell books
class Wizard {
    private String name;

    public Wizard(String name) {
        this.name = name;
    }

    // Method using 'extends' in generics to allow learning from any spell book of Spell or its subclasses
    public void learnSpells(SpellBook<? extends Spell> spellBook) {
        System.out.println(name + " is learning spells:");
        for (Spell spell : spellBook.getSpells()) {
            System.out.println("- " + spell);
        }
    }

    // Method using 'super' in generics to allow writing spells to any spell book of Spell or its superclasses
    public void writeSpell(SpellBook<? super Spell> spellBook, Spell spell) {
        spellBook.addSpell(spell);
        System.out.println(name + " wrote the spell " + spell + " to a spell book");
    }
}

// Main class to demonstrate the practical use of 'super' in generics
public class HogwartsPracticalGenericsSuperExample {
    public static void main(String[] args) {
        // Create spell books
        SpellBook<Spell> generalSpellBook = new SpellBook<>();
        SpellBook<CharmSpell> charmSpellBook = new SpellBook<>();
        SpellBook<TransfigurationSpell> transfigurationSpellBook = new SpellBook<>();

        // Create a wizard
        Wizard harry = new Wizard("Harry Potter");

        // Add spells to books
        charmSpellBook.addSpell(new CharmSpell("Wingardium Leviosa", 5));
        charmSpellBook.addSpell(new CharmSpell("Accio", 7));
        transfigurationSpellBook.addSpell(new TransfigurationSpell("Vera Verto", 8));

        // Demonstrate learning spells (using extends)
        harry.learnSpells(charmSpellBook);
        harry.learnSpells(transfigurationSpellBook);

        // Demonstrate writing spells (using super)
        harry.writeSpell(generalSpellBook, new Spell("Expelliarmus", 6));
        harry.writeSpell(generalSpellBook, new CharmSpell("Lumos", 3));

        // This works because generalSpellBook can accept any Spell or its subclasses
        harry.writeSpell(generalSpellBook, new TransfigurationSpell("Animagus", 10));

        // This would not compile:
        // harry.writeSpell(charmSpellBook, new TransfigurationSpell("Reparifarge", 7));
    }
}