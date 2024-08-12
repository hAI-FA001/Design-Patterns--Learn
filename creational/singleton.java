// only have 1 instance of a class
// provide a way to access this instances globally


public class SingletonPattern {
    public static void main(String[] args) {
        // will give the same instance
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2)
    }
}

class Singleton {
    // private instance and ctor
    private static Singleton instance;
    
    // eager initialization
    // private static Singleton instance = new Singleton()
    
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            // lazy initialization
            instance = new Singleton();
        }
        return instance;
    }
}