package Commands;

import Appliances.Stereo;

public class StereoOff implements Command {
    Stereo stereo;
 
	public StereoOff(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		stereo.off();
	}

	public void undo() {
		stereo.on();
	}
}
