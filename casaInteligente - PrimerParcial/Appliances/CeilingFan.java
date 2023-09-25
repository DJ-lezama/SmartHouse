package Appliances;

import Appliances.Appliance;

public class CeilingFan extends Appliance {
    String location = "";

	public CeilingFan(String location) {
		this.location = location;
	}

	public void on() {
		this.isOn = true;
		System.out.println(location + " ceiling fan is on");

	}

	public void off() {
		this.isOn = false;
		System.out.println(location + " ceiling fan is off");
	}
}
