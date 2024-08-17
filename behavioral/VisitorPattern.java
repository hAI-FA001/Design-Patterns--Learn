// allows adding operations to existing classes w/o modifying them
// i.e. adding a new operation doesn't require adding it to the class (or subclasses)
// e.g. we move operations like calcPrice(), performMaintenance() from the class into Visitor
// the class is no longer modified when adding operations
// sounds like Decorator but this is focused on operations (operations become classes)
// seems like Strategy but focus is on decoupling operations from class (focus is not on making Strategies for operations)

package behavioral;

public class VisitorPattern {
    public static void main(String[] args) {
        RoomElement singleRoom = new SingleRoom();
        RoomElement doubleRoom = new DoubleRoom();
        RoomElement deluxeRoom = new DeluxeRoom();

        singleRoom.accept(new RoomPricingVisitor());
        doubleRoom.accept(new RoomMaintenanceVisitor());
        deluxeRoom.accept(new RoomMaintenanceVisitor());
    }
}

class SingleRoom implements RoomElement {
    int price = 100;

    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}

class RoomPricingVisitor implements RoomVisitor {
    @Override
    public void visit(SingleRoom room) {
        System.out.println("Computing single room price: " + room.price);
    }

    @Override
    public void visit(DoubleRoom room) {
        System.out.println("Computing double room price: " + 2 * room.price);
    }

    @Override
    public void visit(DeluxeRoom room) {
        System.out.println("Computing deluxe room price: " + 10 * room.price);
    }
}

class RoomMaintenanceVisitor implements RoomVisitor {
    @Override
    public void visit(SingleRoom room) {
        System.out.println("Performing maintenance on single room.");
    }

    @Override
    public void visit(DoubleRoom room) {
        System.out.println("Performing maintenance on double room.");
    }

    @Override
    public void visit(DeluxeRoom room) {
        System.out.println("Performing maintenance on deluxe room.");
    }
}

class DoubleRoom implements RoomElement {
    int price = 500;

    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}

class DeluxeRoom implements RoomElement {
    int price = 1000;

    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}

interface RoomVisitor {
    void visit(SingleRoom room);

    void visit(DoubleRoom room);

    void visit(DeluxeRoom room);
}

interface RoomElement {
    void accept(RoomVisitor visitor);
}