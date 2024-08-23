import java.util.Stack;

// The Command interface
interface SpellCommand {
    void cast();
    void undo();
}

// Receiver
class MagicalTarget {
    private String state = "Normal";

    public void applySpell(String effect) {
        this.state = effect;
    }

    public void removeSpell() {
        this.state = "Normal";
    }

    public String getState() {
        return state;
    }
}

// Concrete Command for Expelliarmus spell
class ExpelliarmusSpell implements SpellCommand {
    private MagicalTarget target;

    public ExpelliarmusSpell(MagicalTarget target) {
        this.target = target;
    }

    @Override
    public void cast() {
        target.applySpell("Disarmed");
    }

    @Override
    public void undo() {
        target.removeSpell();
    }
}

// Concrete Command for Stupefy spell
class StupefySpell implements SpellCommand {
    private MagicalTarget target;

    public StupefySpell(MagicalTarget target) {
        this.target = target;
    }

    @Override
    public void cast() {
        target.applySpell("Stunned");
    }

    @Override
    public void undo() {
        target.removeSpell();
    }
}

// Invoker
class Wand {
    private Stack<SpellCommand> spellHistory = new Stack<>();

    public void castSpell(SpellCommand spell) {
        spell.cast();
        spellHistory.push(spell);
    }

    public void undoLastSpell() {
        if (!spellHistory.isEmpty()) {
            SpellCommand spell = spellHistory.pop();
            spell.undo();
        }
    }
}

// Client
public class HogwartsCommandPatternExample {
    public static void main(String[] args) {
        MagicalTarget target = new MagicalTarget();
        Wand wand = new Wand();

        // Cast Expelliarmus
        SpellCommand expelliarmus = new ExpelliarmusSpell(target);
        wand.castSpell(expelliarmus);
        System.out.println("After casting Expelliarmus: " + target.getState());

        // Cast Stupefy
        SpellCommand stupefy = new StupefySpell(target);
        wand.castSpell(stupefy);
        System.out.println("After casting Stupefy: " + target.getState());

        // Undo the last spell (Stupefy)
        wand.undoLastSpell();
        System.out.println("After undoing Stupefy: " + target.getState());

        // Undo again (Expelliarmus)
        wand.undoLastSpell();
        System.out.println("After undoing Expelliarmus: " + target.getState());
    }
}