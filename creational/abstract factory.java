// factory of factory
// client interacts with AbstractFactoryProducer

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactoryProducer.getFactory("Premium");
        
        Car car = factory.getInstance(1000000);
        System.out.println(car);
        
        Car car = factory.getInstance(3000000);
        System.out.println(car);
        
        
        factory = AbstractFactoryProducer.getFactory("Economic");
        Car car = factory.getInstance(100000);
        System.out.println(car);
        
        Car car = factory.getInstance(200000);
        System.out.println(car);
    }
}

public class AbstractFactoryProducer {
    public static AbstractFactory getFactory(String value) {
        if(value == "Economic") {
            return new EconomicCarFactory();
        }
        else if(value == "Luxury" || value == "Premium") {
            return new LuxuryCarFactory();
        }
        return null
    }
}

public interface AbstractFactory {
    public static Car getInstance(int price);
}

public EconomicCarFactory implements AbstractFactory {
    @Override
    public static Car getInstance(int price) {
        if(price <= 100000) {
            return new EconomicCarA();
        }
        else {
            return new EconomicCarB();
        }
    }
}

public LuxuryCarFactory implements AbstractFactory {
    @Override
    public static Car getInstance(int price) {
        if(price >= 1000000 && price < 2000000) {
            return new LuxuryCarA();
        } else if (price > 2000000) {
            retur nenw LuxuryCarB();
        }
        return null;
    }
}

public interface Car {
    public int getTopSpeed();
    
    @Override
    public String toString() {
        return "Car: " + this.getTopSpeed();
    }
}

public class EconomicCarA implements Car {
    @Override
    public int getTopSpeed() {
        100;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nEconomic Car A";
    }
}

public class EconomicCarB implements Car {
    @Override
    public int getTopSpeed() {
        150;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nEconomic Car B";
    }
}

public class LuxuryCarA implements Car {
    @Override
    public int getTopSpeed() {
        200;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\Luxury Car A";
    }
}

public class LuxuryCarB implements Car {
    @Override
    public int getTopSpeed() {
        250;
    }

    @Override
    public String toString() {
        return super.toString() + "\Luxury Car B";
    }
}