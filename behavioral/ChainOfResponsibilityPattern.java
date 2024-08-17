// allows multiple objects to handle a request
// sender doesn't know which object will process it
// e.g. sender calls log()
// there are different objects for different types of logs
// (e.g. 1 obj can only handle INFO logs, another can only handle ERROR),
// the message will be passed internally until an object can handle it

package behavioral;

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        LogProcessor logProcessor = new InfoLogProcesser(new ErrorLogProcesser(null));
        logProcessor.log("[INFO] some info msg");
        logProcessor.log("[ERROR] some error msg");
    }
}

class LogProcessor {
    LogProcessor nextProcessor;

    LogProcessor(LogProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    void log(String message) {
        if (this.nextProcessor != null) {
            this.nextProcessor.log(message);
        }
    }
}

class InfoLogProcesser extends LogProcessor {

    InfoLogProcesser(LogProcessor nextProcessor) {
        super(nextProcessor);
    }

    void log(String message) {
        if (message.toUpperCase().contains("INFO")) {
            System.out.println("Info Log Processor: " + message);
        } else {
            super.log(message);
        }
    }
}

class ErrorLogProcesser extends LogProcessor {

    ErrorLogProcesser(LogProcessor nextProcessor) {
        super(nextProcessor);
    }

    void log(String message) {
        if (message.toUpperCase().contains("ERROR")) {
            System.out.println("Error Log Processor: " + message);
        } else {
            super.log(message);
        }
    }
}