// defines interface/method for creating an object
// allows subclasses/methods to decide which class to instantiate
// hides instantiation logic, provides uniform interface to the client, keeps creation logic in 1 place

public class FactoryPattern {
    public static void main(String[] args) {
        // uniform interface (getLogger()) + class is decided based on argument
        // we don't need to know the logic inside getLogger (how it's created), AKA
        // abstraction
        Logger l = LoggerFactory.getLogger("console");
        l.log("Got console logger");

        l = LoggerFactory.getLogger("file");
        l.log("Got file logger");

        l = LoggerFactory.getLogger("database");
        l.log("Got DB logger");
    }
}

class LoggerFactory {
    public static Logger getLogger(String type) {
        return switch (type) {
            case "console" -> new ConsoleLogger();
            case "file" -> new FileLogger();
            case "database" -> new DatabaseLogger();
            default -> throw new IllegalArgumentException();
        };
    }
}

interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[Console Logger]: " + message);
    };
}

class FileLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[File Logger]: " + message);
    }
}

class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[Database Logger]: " + message);
    }
}