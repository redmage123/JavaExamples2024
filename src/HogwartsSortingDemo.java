interface WizardSkills {
    void castSpell(String spell);
    void useSpecialAbility();
}

abstract class Wizard implements WizardSkills, Comparable<Wizard> {

    protected String name;
    protected String house;
    protected int powerLevel;

    // Constructor
    public Wizard(String name, String house, int powerLevel) {
        this.name = name;
        this.house = house;
        this.powerLevel = powerLevel;
    }

    // Common method for all wizards
    public void showHousePride() {
        System.out.println(name + " proudly represents the house of " + house + "!");
    }

    // Abstract method to be implemented by subclasses
    public abstract void introduceYourself();

    // Implement the compareTo method to compare wizards by powerLevel
    @Override
    public int compareTo(Wizard other) {
        return Integer.compare(this.powerLevel, other.powerLevel);
    }
}

class GryffindorWizard extends Wizard {

    public GryffindorWizard(String name, int powerLevel) {
        super(name, "Gryffindor", powerLevel);
    }

    @Override
    public void castSpell(String spell) {
        System.out.println(name + " of Gryffindor casts " + spell + "!");
    }

    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses Gryffindor's courage to overcome fear!");
    }

    @Override
    public void introduceYourself() {
        System.out.println("I am " + name + " of Gryffindor, brave and daring!");
    }
}

class SlytherinWizard extends Wizard {

    public SlytherinWizard(String name, int powerLevel) {
        super(name, "Slytherin", powerLevel);
    }

    @Override
    public void castSpell(String spell) {
        System.out.println(name + " of Slytherin casts " + spell + "!");
    }

    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses Slytherin's cunning to outsmart the enemy!");
    }

    @Override
    public void introduceYourself() {
        System.out.println("I am " + name + " of Slytherin, ambitious and resourceful!");
    }
}

class RavenclawWizard extends Wizard {

    public RavenclawWizard(String name, int powerLevel) {
        super(name, "Ravenclaw", powerLevel);
    }

    @Override
    public void castSpell(String spell) {
        System.out.println(name + " of Ravenclaw casts " + spell + " with wisdom!");
    }

    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses Ravenclaw's intelligence to solve complex problems!");
    }

    @Override
    public void introduceYourself() {
        System.out.println("I am " + name + " of Ravenclaw, wise and creative!");
    }
}

class HufflepuffWizard extends Wizard {

    public HufflepuffWizard(String name, int powerLevel) {
        super(name, "Hufflepuff", powerLevel);
    }

    @Override
    public void castSpell(String spell) {
        System.out.println(name + " of Hufflepuff casts " + spell + " with dedication!");
    }

    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses Hufflepuff's loyalty to protect friends!");
    }

    @Override
    public void introduceYourself() {
        System.out.println("I am " + name + " of Hufflepuff, patient and fair!");
    }
}

public class HogwartsSortingDemo {
    public static void main(String[] args) {
        // Create instances of various types of wizards
        Wizard harry = new GryffindorWizard("Harry Potter", 95);
        Wizard draco = new SlytherinWizard("Draco Malfoy", 85);
        Wizard luna = new RavenclawWizard("Luna Lovegood", 90);
        Wizard cedric = new HufflepuffWizard("Cedric Diggory", 88);

        // Store them in an array of Wizards
        Wizard[] wizards = { harry, draco, luna, cedric };

        // Interact with each wizard using methods from the abstract class and the interface
        for (Wizard wizard : wizards) {
            wizard.introduceYourself();  // Abstract method implemented in each subclass
            wizard.showHousePride();     // Common method from the abstract class
            wizard.castSpell("Expelliarmus");
            wizard.useSpecialAbility();
            System.out.println();
        }

        // Sort the wizards by power level
        java.util.Arrays.sort(wizards);

        // Display sorted wizards
        System.out.println("Wizards sorted by power level:");
        for (Wizard wizard : wizards) {
            System.out.println(wizard.name + " (" + wizard.house + ") - Power Level: " + wizard.powerLevel);
        }
    }
}
