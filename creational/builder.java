// separates construction of object from its final representation (same construction can give different representations)
// internal construction logic is also hidden

public class BuilderPattern {
    public static void main(String[] args) {
        Computer c = new Computer.ComuterBuilder("HDD", "16GB RAM")
        // can optionally set these using Builder
        .setHasGraphicsCard(true)
        .setHasBluetooth(true)
        .build();

        System.out.println(c);
    }
}

class Computer {
    private String hdd;
    private String ram;
    
    // optional
    private boolean hasGraphicsCard;
    private boolean hasBluetooth;

    public String getHDD() { return hdd; }
    public String getRAM() { return ram; }
    public boolean getHasGraphicsCard() { return hasGraphicsCard; }
    public boolean getHasBluetooth() { return hasBluetooth; }

    // force client to use builder
    private Computer(ComputerBuilder builder) {
        this.hdd = builder.hdd;
        this.ram = builder.ram;
        this.hasGraphicsCard = builder.hasGraphicsCard;
        this.hasBluetooth = builder.hasBluetooth;
    }

    @Override
    public String toString() {
        return "HDD: " + this.getHDD() + ", RAM: " + this.getRAM()
        + "\nGPU?: " + this.getHasGraphicsCard()
        + "\nBluetooth?: " + this.getHasBluetooth();
    }


    public static class ComputerBuilder {
        private String hdd;
        private String ram;
        
        // optional
        private boolean hasGraphicsCard;
        private boolean hasBluetooth;

        public ComputerBuilder(String hdd, String ram) {
            this.hdd = hdd;
            this.ram = ram;
        }
    }

    // these return the builder instance itself
    public ComputerBuilder setHasGraphicsCard(boolean hasGraphicsCard) {
        this.hasGraphicsCard = hasGraphicsCard;
        
        return this;
    }

    public ComputerBuilder setHasBluetooth(boolean hasBluetooth) {
        this.hasBluetooth = hasBluetooth;
        
        return this;
    }


    // this finally creates the Computer
    public Computer build() { return new Computer(this); }
}