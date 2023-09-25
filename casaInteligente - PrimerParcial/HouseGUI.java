import Appliances.Appliance;
import ObserverPattern.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class HouseGUI extends JFrame implements IObserver { //HouseGUI needs to get updates from the RemoteControlGUI

    //HouseGUI's buttons will change their icon depending on the state of the appliance they represent
    ArrayList<JButton> buttonList = new ArrayList<>(); //list of all buttons on the HouseGUI

    //JButtons and JLabels declaration
    JButton livingroomLightButton = new JButton(" is off ");
    JButton kitchenLightButton = new JButton(" is off ");
    JButton ceilingFanButton = new JButton(" is off ");
    JButton garageDoorButton = new JButton(" is off ");
    JButton stereoButton = new JButton(" is off ");
    JButton atticLightButton = new JButton(" is off ");
    JButton boilerButton = new JButton(" is off ");

    //ObserverPattern.Observer implementation
    @Override
    public void Update(ArrayList<Appliance> appliances){ //gets subject, isOn state, row, appliance

        for (int i = 0; i < appliances.size(); i++){

            if (appliances.get(i).isOn) {
                buttonList.get(i).setText("is on");
                buttonList.get(i).setBackground(Color.green);
            }
            else {
                buttonList.get(i).setText("is off");
                buttonList.get(i).setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    public HouseGUI() { //GUI constructor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 720);

        // Load the background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("casaInteligente - PrimerParcial/Images/house.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scale the background image to fit the size of the frame
        Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create a JLabel with the background image
        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 800, 700);
        getContentPane().add(backgroundLabel);

        //custom placing of the JButtons and JLabels
        livingroomLightButton.setBounds(210, 390, 100, 40);
        livingroomLightButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(livingroomLightButton);

        kitchenLightButton.setBounds(515, 390, 100, 40);
        kitchenLightButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(kitchenLightButton);

        ceilingFanButton.setBounds(350, 211, 100, 40);
        ceilingFanButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(ceilingFanButton);

        garageDoorButton.setBounds(331, 560, 150, 40);
        garageDoorButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(garageDoorButton);

        stereoButton.setBounds(170, 211, 100, 40);
        stereoButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(stereoButton);

        atticLightButton.setBounds(350, 70, 100, 40);
        atticLightButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(atticLightButton);

        boilerButton.setBounds(540, 211, 100, 40);
        boilerButton.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(boilerButton);

        //adding all buttons to the buttons arraylist
        buttonList.add(livingroomLightButton);
        buttonList.add(kitchenLightButton);
        buttonList.add(ceilingFanButton);
        buttonList.add(garageDoorButton);
        buttonList.add(stereoButton);
        buttonList.add(atticLightButton);
        buttonList.add(boilerButton);

        setLayout(null); // Use null layout for custom button placement
    }
}



