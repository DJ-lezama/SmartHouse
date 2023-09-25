import javax.swing.*;

public class RemoteControlTest {
    public static void main(String [] args) {
        RemoteControlGUI remote = new RemoteControlGUI();
        remote.masterControl.registerObserver(remote);
        remote.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            HouseGUI houseGUI = new HouseGUI();
            remote.masterControl.registerObserver(houseGUI);
            houseGUI.setVisible(true);
        });
    }  
}