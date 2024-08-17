// turns requests/commands into objects
// can parametrize or queue commands, pass them to methods, etc
// decouples the Sender and Receiver
// e.g. if "turn AC on" needed 10 methods to be called, then Sender would need to know and invoke them
// e.g. if have tons of similar classes that do different things, would lead to tons of subclasses
// e.g. CopyingText can be done via buttons, context menu, other places -> would need to duplicate code if don't turn Copying into its own class
// seems like Facade (hiding complexity) but is different (we turn commands/requests into objects)

package behavioral;

public class CommandPattern {
    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner();
        // sender needs to know the commands
        ac.turnOnAC();
        ac.setTemp(24);
        ac.turnOffAC();

        // sender only needs to call pressButton()
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(new TurnOnACCommand(ac));
        remoteControl.pressButton();

        remoteControl.setCommand(new TurnOffACCommand(ac));
        remoteControl.pressButton();
    }
}

class RemoteControl {
    ICommand command;

    void setCommand(ICommand command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}

// for each command we want to expose, we make a class like this
class TurnOnACCommand implements ICommand {
    AirConditioner ac;

    TurnOnACCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOnAC();
    }
}

class TurnOffACCommand implements ICommand {
    AirConditioner ac;

    TurnOffACCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOffAC();
    }
}

interface ICommand {
    void execute();
}

class AirConditioner {
    boolean isOn;
    int temp;

    void turnOnAC() {
        isOn = true;
        System.out.println("Turned on AC");
    }

    void turnOffAC() {
        isOn = false;
        System.out.println("Turned off AC");
    }

    void setTemp(int temp) {
        this.temp = temp;
        System.out.println("Changed temp to " + temp);
    }
}