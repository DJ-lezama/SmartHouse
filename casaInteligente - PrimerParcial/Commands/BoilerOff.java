package Commands;

import Appliances.Boiler;

public class BoilerOff implements Command {
    Boiler boiler;

    public BoilerOff(Boiler boiler) {
        this.boiler = boiler;
    }

    public void execute() {
        boiler.off();
    }

    public void undo() {
        boiler.on();
    }
}