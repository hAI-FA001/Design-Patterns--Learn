// prototype helps in cloning (don't need to create new objects from scratch)
// new objs are made by copying/cloning the prototype (so we have something to start with)
// can be a shallow copy or a deep copy
// useful when object creation is expensive
// e.g. new Obj() then a complex setup process like obj.readFromFile() => expensive, so clone an existing object and reuse its properties instead of calling readFromFile() again
// client also doesn't need to know about the cloning logic or the details of the class either (would need this knowledge if we did the cloning in main() ourselves)

import java.util.HashMap;
import java.util.Map;

public class PrototypePattern {
    public static void main(String[] args) {
        // can use the registry to get objects
        PrototypeRegistry reg = new PrototypeRegistry();

        Circle circleProto = new Circle(1, new int[] { 1 });
        reg.addPrototype("CircleWithRadius1", circleProto);

        Circle circleProto2 = new Circle(10, new int[] { 1, 2, 3 });
        reg.addPrototype("CircleWithRadius10", circleProto2);

        try {
            Circle clonedCircle = (Circle) reg.getPrototype("CircleWithRadius1");
            Circle clonedCircle2 = (Circle) reg.getPrototype("CircleWithRadius10");

            // to show deep copying
            clonedCircle2.getDimensions()[0] = 9;

            System.out.println("Circle: " + circleProto);
            System.out.println("Cloned Circle: " + clonedCircle);
            System.out.println("Circle 2: " + circleProto2);
            System.out.println("Cloned Circle 2: " + clonedCircle2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

interface Prototype extends Cloneable {
    Prototype clone() throws CloneNotSupportedException;
}

class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();

    public void addPrototype(String key, Prototype prototype) {
        this.prototypes.put(key, prototype);
    }

    public Prototype getPrototype(String key) throws CloneNotSupportedException {
        Prototype prototype = this.prototypes.get(key);
        if (prototype != null) {
            return prototype.clone();
        }
        return null;
    }
}

class Circle implements Prototype {
    private int radius;
    private int[] dimensions;

    public int getRadius() {
        return this.radius;
    }

    public int[] getDimensions() {
        return this.dimensions;
    }

    public Circle(int radius, int[] dimensions) {
        this.radius = radius;
        this.dimensions = dimensions;
    }

    @Override
    public Circle clone() throws CloneNotSupportedException {
        // shallow copy
        // return (Circle) super.clone();

        Circle clone = (Circle) super.clone();
        // deep copy for the array
        clone.dimensions = dimensions.clone();
        return clone;
    }

    @Override
    public String toString() {
        String dimsStr = "[";
        for (int dim : this.getDimensions()) {
            dimsStr += dim + ", ";
        }
        dimsStr += "]";
        return "Circle: " + this.hashCode()
                + "\nradius: " + this.getRadius() + ", dims: " + dimsStr;
    }
}