interface WizardSkills {
    void castSpell(String spell);  // Abstract method
    void useSpecialAbility();  // Abstract method
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

    // We could still have common methods here that don't belong to the interface
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

public class HogwartswizardInterfaceDemo {
    public static void main(String[] args) {
        // Create instances of GryffindorWizard and SlytherinWizard
        WizardSkills harry = new GryffindorWizard("Harry Potter", 95);
        WizardSkills draco = new SlytherinWizard("Draco Malfoy", 85);

        // Store them in an array of WizardSkills
        WizardSkills[] wizards = { harry, draco };

        // Interact with each wizard using the interface methods
        for (WizardSkills wizard : wizards) {
            wizard.castSpell("Expelliarmus");
            wizard.useSpecialAbility();
            System.out.println();
        }
    }
}



