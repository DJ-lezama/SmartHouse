package Commands;

import Appliances.GarageDoor;

public class GarageDoorUp implements Command{
    GarageDoor garageDoor;
 
	public GarageDoorUp(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}
 
	public void execute() {
		garageDoor.up();
	}

	public void undo() {
		garageDoor.down();
	}
}
