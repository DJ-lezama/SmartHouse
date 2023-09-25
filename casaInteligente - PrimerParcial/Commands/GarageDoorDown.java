package Commands;

import Appliances.GarageDoor;

public class GarageDoorDown implements Command {
    GarageDoor garageDoor;
 
	public GarageDoorDown(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}
 
	public void execute() {
		garageDoor.down();
	}

	public void undo() {
		garageDoor.up();
	}
}
