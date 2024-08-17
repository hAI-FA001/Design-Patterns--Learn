// objs refer through a mediator
// Mediator maintains a list of objs, objs don't talk to each other directly

package behavioral;

import java.util.ArrayList;
import java.util.List;

public class MediatorPattern {
    public static void main(String[] args) {
        AuctionMediator mediator = new Auction();
        Colleague bidder1 = new Bidder("A", mediator);
        Colleague bidder2 = new Bidder("B", mediator);

        bidder1.placeBid(123);
        bidder2.placeBid(456);
        bidder1.placeBid(789);
    }
}

class Auction implements AuctionMediator {
    List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void addBidder(Colleague bidder) {
        colleagues.add(bidder);
    }

    @Override
    public void placeBid(Colleague bidder, int amount) {
        for (Colleague c : colleagues) {
            if (!c.getName().equals(bidder.getName())) {
                c.receiveBidNotif(amount);
            }
        }
    }
}

class Bidder implements Colleague {
    String name;
    AuctionMediator mediator;

    public Bidder(String name, AuctionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.addBidder(this);
    }

    @Override
    public void placeBid(int amount) {
        mediator.placeBid(this, amount);
    }

    @Override
    public void receiveBidNotif(int amount) {
        System.out.println(hashCode() + " was notified of someone's bid of amount " + amount);
    }

    @Override
    public String getName() {
        return name;
    }
}

interface AuctionMediator {
    void addBidder(Colleague bidder);

    void placeBid(Colleague bidder, int amount);
}

interface Colleague {
    // equivalent of sendMessage() to mediator
    void placeBid(int amount);

    // equivalent of receiveMessage() from mediator
    void receiveBidNotif(int amount);

    String getName();
}