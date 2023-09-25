package Commands;

import Appliances.CeilingFan;

public class CeilingFanOn implements Command {
	CeilingFan fan;
 
	public CeilingFanOn(CeilingFan fan) {
		this.fan = fan;
	}
 
	public void execute() {
		fan.on();
	}

	public void undo() {
		fan.off();
	}
}