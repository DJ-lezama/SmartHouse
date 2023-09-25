package Appliances;

import Appliances.Appliance;

public class Stereo extends Appliance {
    String location = "";

	public Stereo(String location) {
		this.location = location;
	}

	public void on() {
		this.isOn = true;
		System.out.println(location + " stereo is on");
	}

	public void off() {
		this.isOn = false;
		System.out.println(location + " stereo is off");
	}
}
