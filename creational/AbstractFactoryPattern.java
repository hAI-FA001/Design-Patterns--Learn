// factory of factory
// client interacts with AbstractFactoryProducer

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactoryProducer.getFactory("Premium");

        Car car = factory.getInstance(1000000);
        System.out.println(car);

        car = factory.getInstance(3000000);
        System.out.println(car);

        factory = AbstractFactoryProducer.getFactory("Economic");
        car = factory.getInstance(100000);
        System.out.println(car);

        car = factory.getInstance(200000);
        System.out.println(car);
    }
}

class AbstractFactoryProducer {
    public static AbstractFactory getFactory(String value) {
        if (value == "Economic") {
            return new EconomicCarFactory();
        } else if (value == "Luxury" || value == "Premium") {
            return new LuxuryCarFactory();
        }
        return null;
    }
}

interface AbstractFactory {
    public Car getInstance(int price);
}

class EconomicCarFactory implements AbstractFactory {

    @Override
    public Car getInstance(int price) {
        if (price <= 100000) {
            return new EconomicCarA();
        } else {
            return new EconomicCarB();
        }
    }
}

class LuxuryCarFactory implements AbstractFactory {

    @Override
    public Car getInstance(int price) {
        if (price >= 1000000 && price < 2000000) {
            return new LuxuryCarA();
        } else if (price > 2000000) {
            return new LuxuryCarB();
        }
        return null;
    }
}

abstract class Car {
    public abstract int getTopSpeed();

    @Override
    public String toString() {
        return "Car: " + this.getTopSpeed();
    }
}

class EconomicCarA extends Car {
    @Override
    public int getTopSpeed() {
        return 100;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEconomic Car A";
    }
}

class EconomicCarB extends Car {
    @Override
    public int getTopSpeed() {
        return 150;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEconomic Car B";
    }
}

class LuxuryCarA extends Car {
    @Override
    public int getTopSpeed() {
        return 200;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLuxury Car A";
    }
}

class LuxuryCarB extends Car {
    @Override
    public int getTopSpeed() {
        return 250;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLuxury Car B";
    }
}