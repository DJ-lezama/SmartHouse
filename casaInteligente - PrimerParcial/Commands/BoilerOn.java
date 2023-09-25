package Commands;

import Appliances.Boiler;

public class BoilerOn implements Command {
    Boiler boiler;

    public BoilerOn(Boiler boiler) {
        this.boiler = boiler;
    }

    public void execute() {
        boiler.on();
    }

    public void undo() {
        boiler.off();
    }
}