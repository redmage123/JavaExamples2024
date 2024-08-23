interface WizardSkills {
    void castSpell(String spell);
    void useSpecialAbility();
}

abstract class Wizard {
    protected String name;
    protected String house;
    protected int powerLevel;

    public Wizard(String name, String house, int powerLevel) {
        this.name = name;
        this.house = house;
        this.powerLevel = powerLevel;
    }
}

class GryffindorWizard extends Wizard implements WizardSkills {

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
}

class SlytherinWizard extends Wizard implements WizardSkills {

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
}

class RavenclawWizard extends Wizard implements WizardSkills {

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
}

class HufflepuffWizard extends Wizard implements WizardSkills {

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
}

public class HogwartsPolymorphismDemo {
    public static void main(String[] args) {
        // Create instances of various types of wizards
        WizardSkills harry = new GryffindorWizard("Harry Potter", 95);
        WizardSkills draco = new SlytherinWizard("Draco Malfoy", 85);
        WizardSkills luna = new RavenclawWizard("Luna Lovegood", 90);
        WizardSkills cedric = new HufflepuffWizard("Cedric Diggory", 88);

        // Store them in an array of WizardSkills
        WizardSkills[] wizards = { harry, draco, luna, cedric };

        // Interact with each wizard using the interface methods
        for (WizardSkills wizard : wizards) {
            wizard.castSpell("Expelliarmus");
            wizard.useSpecialAbility();
            System.out.println();
        }
    }
}