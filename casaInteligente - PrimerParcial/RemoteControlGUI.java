import Appliances.*;
import Commands.*;
import ObserverPattern.IObserver;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class RemoteControlGUI extends JFrame implements IObserver { //RemoteControlGUI is the observable

    MasterControl masterControl = new MasterControl();

    //appliances declaration
    Light livingRoomLight = new Light("Living Room Appliances.Light ");
    LightOnCommand livingroomLightOn = new LightOnCommand(livingRoomLight);
    LightOffCommand livingroomLightOff = new LightOffCommand(livingRoomLight);

    Light kitchenLight = new Light("Kitchen Appliances.Light ");
    LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight );
    LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight );

    CeilingFan ceilingfan = new CeilingFan("");
    CeilingFanOn ceilingFanOn = new CeilingFanOn(ceilingfan);
    CeilingFanOff ceilingFanOff = new CeilingFanOff(ceilingfan);

    GarageDoor garageDoor = new GarageDoor("");
    GarageDoorUp garageOpen = new GarageDoorUp(garageDoor);
    GarageDoorDown garageClose = new GarageDoorDown(garageDoor);

    Stereo stereo = new Stereo("");
    StereoOn stereoOn = new StereoOn(stereo);
    StereoOff stereoOff = new StereoOff(stereo);

    Light atticLight = new Light("Attic light");
    LightOnCommand atticLightOn = new LightOnCommand(atticLight);
    LightOffCommand atticLightOff = new LightOffCommand(atticLight);

    Boiler boiler = new Boiler("Bathroom boiler");
    BoilerOn boilerOn = new BoilerOn(boiler);
    BoilerOff boilerOff = new BoilerOff(boiler);

    JButton buttonsArray[][] = new JButton[9][2]; //Button array
    public int row, column;

    //button icons
    ImageIcon On = new ImageIcon("Images/On.jpeg");
    ImageIcon Off = new ImageIcon("Images/Off.jpeg");


    //////ObserverPattern.Observer implementation
    @Override
    public void Update(ArrayList<Appliance> appliances) {
        changeImage();
    }

    public RemoteControlGUI() { //remote constructor

        Container container = getContentPane(); //container creation
        container.setLayout(new GridLayout(9,2)); //Layout definition

        masterControl.setCommand(0, livingroomLightOn, livingroomLightOff);
        masterControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        masterControl.setCommand(2, ceilingFanOn, ceilingFanOff);
        masterControl.setCommand(3, garageOpen, garageClose);
        masterControl.setCommand(4, stereoOn, stereoOff);
        masterControl.setCommand(5, atticLightOn, atticLightOff);
        masterControl.setCommand(6, boilerOn, boilerOff);

        //adding appliances to appliances list
        masterControl.appliancesList.add(livingRoomLight);
        masterControl.appliancesList.add(kitchenLight);
        masterControl.appliancesList.add(ceilingfan);
        masterControl.appliancesList.add(garageDoor);
        masterControl.appliancesList.add(stereo);
        masterControl.appliancesList.add(atticLight);
        masterControl.appliancesList.add(boiler);

        for (row = 0; row < 9; row++) //loop to run through the button array - constructor
        {
            for (column = 0; column < 2; column++) 
            {
                switch (column)
                {
                    case 0:
                        switch (row)
                        {
                            case 0:
                                buttonsArray[row][column] = new JButton("Living Room Lights");
                            break;
                            case 1:
                                buttonsArray[row][column] = new JButton("Kitchen Lights");
                            break;
                            case 2:
                                buttonsArray[row][column] = new JButton("Ceiling Fan");
                            break;
                            case 3:
                                buttonsArray[row][column] = new JButton("Garage door");
                            break;
                            case 4:
                                buttonsArray[row][column] = new JButton("Stereo");
                            break;
                            case 5:
                                buttonsArray[row][column] = new JButton("Attic light");
                                break;
                            case 6:
                                buttonsArray[row][column] = new JButton("Boiler");
                                break;
                            case 7:
                                buttonsArray[row][column] = new JButton("Vacation Mode");
                                break;
                            case 8:
                                buttonsArray[row][column] = new JButton("Undo all");
                            break;
                        }
                    break;

                    case 1: 
                        if (row != 8) {
                            buttonsArray[row][column] = new JButton(Off); //icon Off as default button icon
                        }
                        else {
                            buttonsArray[row][column] = new JButton("Undo last action");
                        }
                    break;
                }  

                buttonsArray[row][column].putClientProperty("row", row);
                buttonsArray[row][column].putClientProperty("column", column);
                
                container.add(buttonsArray[row][column]); //adds button array to container

                ButtonHandler handler = new ButtonHandler(); //handler creation
                buttonsArray[row][column].addActionListener(handler); //adds action listener to buttons array

                setSize(450, 800);
            }
        }
    }



    private class ButtonHandler implements ActionListener {
        //handle button event
        public void actionPerformed(ActionEvent event)
        {
            JButton source = (JButton) event.getSource(); //get action source
            int row = (int) source.getClientProperty("row"); //gets row from button source of the action
            int column = (int) source.getClientProperty("column"); //gets column from the button source of the action

            if (masterControl.GetVacMode()) {
                System.out.println("Ending Vacation Mode");
                masterControl.stopVacacionesMode();
                buttonsArray[7][1].setIcon(Off);

            }
            else {
                if(row < 7 && masterControl.appliancesList.get(row).isOn)
                {
                    //if the appliance is on then it will be turned off when the button is pressed
                    masterControl.ButtonPressed_OFF(row);
                }
                else if (row < 7 && !masterControl.appliancesList.get(row).isOn)
                {
                    //if the appliance is off then it will be turned on when the button is pressed
                    masterControl.ButtonPressed_ON(row);
                }
                if (row == 7)
                {
                    masterControl.startVacacionesMode();
                    buttonsArray[7][1].setIcon(On);

                }

                if (row == 8 )
                {
                    switch (column){
                        case 0: //undo all actions within the commands stack
                            masterControl.UndoAll();
                            break;

                        case 1: //undo last action
                            masterControl.Undo_1();
                            break;
                    }
                }
            }
            // updates on/off images
            changeImage();
        }
    }

    public void changeImage(){

        for (int i = 0; i< masterControl.appliancesList.size(); i++) {
            if (masterControl.appliancesList.get(i).isOn)
            {
                buttonsArray[i][1].setIcon(On);
            }
            else {
                buttonsArray[i][1].setIcon(Off);
            }
        }
    }
}
