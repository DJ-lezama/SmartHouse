package Appliances;

import Appliances.Appliance;

public class GarageDoor extends Appliance {
    String location;

    public GarageDoor(String location) {
		this.location = location;
	}

	public void up() {
		this.isOn = true;
		System.out.println(location + " garage Door is Up");
	}

	public void down() {
		this.isOn = false;
		System.out.println(location + " garage Door is Down");
	}
}
