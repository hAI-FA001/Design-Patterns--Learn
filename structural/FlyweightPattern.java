// helps reduce memory usage by sharing data
// intrinsic data: shared, remains same once defined
// extrinsic data: changes based on client input, differs b/w objects
// move all intrinsic data into its own object -> Flyweight object
// extrinsic can be passed as param
// caching can be used for Flyweight object

package structural;

import java.util.Map;
import java.util.HashMap;

public class FlyweightPattern {
    public static void main(String[] args) {
        // e.g. making tons of robots for a game -> lots of mem usage
        // (if sprite is 31KB, then total mem used is 31GB)
        int x = 0, y = 0;
        for (int i = 0; i < 5_000_000; i++) {
            Sprites humanoidSprite = new Sprites();
            BadRobot humanoidRobo = new BadRobot(x + i, y + i, "HUMANOID", humanoidSprite);
        }
        for (int i = 0; i < 5_000_000; i++) {
            Sprites roboDogSprite = new Sprites();
            BadRobot roboDogRobo = new BadRobot(x + i, y + i, "ROBODOG", roboDogSprite);
        }

        Robot humanoidRobo1 = RoboFactory.createRobot("HUMANOID");
        Robot humanoidRobo2 = RoboFactory.createRobot("HUMANOID"); // will get it from cache
        // same hash codes
        // pass extrinsic data
        humanoidRobo1.display(1, 2);
        humanoidRobo2.display(3, 4);
    }
}

class RoboFactory {
    static Map<String, Robot> roboCache = new HashMap<>();

    static Robot createRobot(String type) {
        if (roboCache.containsKey(type)) {
            return roboCache.get(type);
        }

        if (type == "HUMANOID") {
            Sprites humanoidSprites = new Sprites();
            Robot humanoidRobo = new HumanoidRobot(type, humanoidSprites);
            roboCache.put(type, humanoidRobo);

            return humanoidRobo;
        } else if (type == "ROBODOG") {
            Sprites roboSprites = new Sprites();
            Robot roboDog = new RoboDog(type, roboSprites);
            roboCache.put(type, roboDog);

            return roboDog;
        }
        return null;
    }
}

class HumanoidRobot implements Robot {
    // keep only intrinsic data
    String type;
    Sprites body;

    HumanoidRobot(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    // extrinsic data is passed as parameter here
    @Override
    public void display(int x, int y) {
        System.out.println("Displaying Humanoid " + hashCode() + " at " + x + ", " + y);
    }
}

class RoboDog implements Robot {
    String type;
    Sprites body;

    RoboDog(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying RoboDog " + hashCode() + " at " + x + ", " + y);
    }
}

interface Robot {
    void display(int x, int y);
}

class BadRobot {
    int xCoord, yCoord;
    String type;
    Sprites body;

    public BadRobot(int xCoord, int yCoord, String type, Sprites body) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.type = type;
        this.body = body;
    }

}

// e.g. some 2D array or bitmap
class Sprites {
}