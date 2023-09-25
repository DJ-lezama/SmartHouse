package Appliances;//import java.util.ArrayList;

import Appliances.Appliance;

public class Light extends Appliance {
	String location = "";

	public Light(String location) {
		this.location = location;
	}

	public void on() {
		if (isOn == false)
		{
			System.out.println(location + " light is on");
			this.isOn = true;
		}
	}

	public void off() {
		if (isOn == true) {
			System.out.println(location + " light is off");
			this.isOn = false;
		}
	}

		
}