// helps in reverting obj to previous state
// doesn't expose internal impl
// 3 components: Originator (obj), Memento (holds state), Caretaker (manages list of states/Memento)

package behavioral;

import java.util.ArrayList;
import java.util.List;

public class MementoPattern {
    public static void main(String[] args) {
        ConfigCareTaker careTaker = new ConfigCareTaker();
        ConfigOriginator originator = new ConfigOriginator(5, 10);
        System.out.println(originator);

        ConfigMemento snapshot1 = originator.createMemento();
        careTaker.addMemento(snapshot1);

        originator.setHeight(200);
        originator.setWidth(200);
        System.out.println(originator);

        ConfigMemento snapshot2 = originator.createMemento();
        careTaker.addMemento(snapshot2);

        originator.setHeight(111);
        originator.setWidth(333);
        System.out.println(originator);

        ConfigMemento snapshot3 = originator.createMemento();
        careTaker.addMemento(snapshot3);

        ConfigMemento restored = careTaker.undo();
        restored = careTaker.undo();
        restored = careTaker.undo();
        originator.restoreMemento(restored);

        System.out.println(originator);
    }
}

class ConfigMemento {
    int height, width;

    public ConfigMemento(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}

class ConfigCareTaker {
    List<ConfigMemento> history = new ArrayList<>();

    public void addMemento(ConfigMemento memento) {
        this.history.add(memento);
    }

    public ConfigMemento undo() {
        if (!history.isEmpty()) {
            int lastMementoIdx = history.size() - 1;
            ConfigMemento lastMemento = history.get(lastMementoIdx);
            history.remove(lastMementoIdx);
            return lastMemento;
        }
        return null;
    }
}

class ConfigOriginator {
    private int height, width;

    ConfigOriginator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public ConfigMemento createMemento() {
        return new ConfigMemento(this.height, this.width);
    }

    public void restoreMemento(ConfigMemento memento) {
        this.height = memento.getHeight();
        this.width = memento.getWidth();
    }

    @Override
    public String toString() {
        return "(w, h) = " + "(" + getWidth() + ", " + getHeight() + ")";
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
