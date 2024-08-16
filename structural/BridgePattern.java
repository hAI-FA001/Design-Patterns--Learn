// decouples abstraction from implementation
// issue e.g. class LivingThings w/ breatheProcess(), other classes (Dog, Tree etc) implement it, if want to add a new process then need to implement it
// (abstraction of breathing is tightly coupled with implementation)
// looks similar to Strategy but the idea is to decouple/move the implemention inside a class into its own class (e.g. move logic from Tree into its own class LandBreathe)
// (now, adding new breathing process does not require adding new LivingThing)
// the implementation (LivingThing in this case) and the abstraction (BreatheImplementor) can grow independently

package structural;

public class BridgePattern {
    public static void main(String[] args) {
        LivingThings tree = new Tree(new LandBreathe());
        LivingThings fish = new Fish(new WaterBreathe());

        tree.breatheProcess();
        fish.breatheProcess();
    }
}

// the abstraction
interface BreatheImplementor {
    void breatheProcess();
}

// the implementation
abstract class LivingThings {
    BreatheImplementor brImpl;

    LivingThings(BreatheImplementor brImpl) {
        this.brImpl = brImpl;
    }

    abstract void breatheProcess();
}

// to add a new breathe process, we make these classes
// (not coupled with making a new LivingThing)
class LandBreathe implements BreatheImplementor {
    public void breatheProcess() {
        System.out.println("(Breathing process on Land)");
    }
}

class WaterBreathe implements BreatheImplementor {
    public void breatheProcess() {
        System.out.println("(Breathing process on Water)");
    }
}

class Fish extends LivingThings {
    Fish(BreatheImplementor brImpl) {
        super(brImpl);
    }

    @Override
    void breatheProcess() {
        System.out.println("Fish " + hashCode() + " is breathing.");
        brImpl.breatheProcess();
    }
}

class Tree extends LivingThings {
    Tree(BreatheImplementor brImpl) {
        super(brImpl);
    }

    @Override
    void breatheProcess() {
        System.out.println("Tree " + hashCode() + " is breathing.");
        brImpl.breatheProcess();
    }
}
