abstract class Wizard {
    protected String name;
    protected String house;
    protected int powerLevel;

    public Wizard(String name, String house, int powerLevel) {
        this.name = name;
        this.house = house;
        this.powerLevel = powerLevel;
    }

    public void castSpell(String spell) {
        System.out.println(name + " casts " + spell + "!");
    }

    public abstract void useSpecialAbility();
}

class GryffindorWizard extends Wizard {

    public GryffindorWizard(String name, int powerLevel) {
        super(name, "Gryffindor", powerLevel);
    }

    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses Gryffindor's courage to overcome fear!");
    }
}

class SlytherinWizard extends Wizard {

    public SlytherinWizard(String name, int powerLevel) {
        super(name, "Slytherin", powerLevel);
    }

    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses Slytherin's cunning to outsmart the enemy!");
    }
}

public class HogwartsDemo {
    public static void main(String[] args) {
        Wizard harry = new GryffindorWizard("Harry Potter", 95);
        Wizard draco = new SlytherinWizard("Draco Malfoy", 85);

        harry.castSpell("Expelliarmus");
        harry.useSpecialAbility();

        draco.castSpell("Serpensortia");
        draco.useSpecialAbility();
    }
}
