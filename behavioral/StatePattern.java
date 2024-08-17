// alters behavior when internal state changes

package behavioral;

public class StatePattern {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.setMachineState(new IdleState());
        machine.getState().insertCoin(machine); // changes state
        machine.getState().insertCoin(machine); // nothing
        machine.getState().dispenseItem(machine); // change state
        machine.getState().dispenseItem(machine); // nothing
    }
}

interface VendingMachineState {
    void insertCoin(VendingMachine product);

    void dispenseItem(VendingMachine product);
}

class IdleState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachine product) {
        System.out.println("Coin inserted.");
        product.setMachineState(new WorkingState());
    }

    @Override
    public void dispenseItem(VendingMachine product) {
        // not doing anything here
    }
}

class WorkingState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachine product) {
        // not doing anything here
    }

    @Override
    public void dispenseItem(VendingMachine product) {
        System.out.println("Item dispensed.");
        product.setMachineState(new IdleState());
    }
}

class VendingMachine {
    VendingMachineState machineState;

    public VendingMachineState getState() {
        return machineState;
    }

    public void setMachineState(VendingMachineState machineState) {
        this.machineState = machineState;
    }
}