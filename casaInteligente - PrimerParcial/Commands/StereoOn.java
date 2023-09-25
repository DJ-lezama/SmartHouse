package Commands;

import Appliances.Stereo;

public class StereoOn implements Command {
    Stereo stereo;
 
	public StereoOn(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		stereo.on();
	}

	public void undo() {
		stereo.off();
	}
}
