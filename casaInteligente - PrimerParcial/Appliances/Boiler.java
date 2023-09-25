package Appliances;

import Appliances.Appliance;

public class Boiler extends Appliance {
    String location = "";

    public Boiler(String location) {
        this.location = location;
    }
    public void on() {
        this.isOn = true;
        System.out.println(location + " boiler is on");
    }

    public void off() {
        this.isOn = false;
        System.out.println(location + " boiler is off");
    }
}
