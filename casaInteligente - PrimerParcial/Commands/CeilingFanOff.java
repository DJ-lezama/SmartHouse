package Commands;

import Appliances.CeilingFan;

public class CeilingFanOff implements Command {
	CeilingFan fan;
 
	public CeilingFanOff(CeilingFan fan) {
		this.fan = fan;
	}
 
	public void execute() {
		fan.off();
	}

	public void undo() {
		fan.on();
	}
}
