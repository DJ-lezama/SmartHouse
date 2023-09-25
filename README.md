# Smart House
This program simulates a smart house and its remote control, from which the user can turn on and off different appliances like a stereo, kitchen, attic, and living room lights, boiler, open and close the garage door, etc.<br>
Three different design patterns were used for this project:

-Singleton pattern: To only have one instance of the class MasterControl (one of the main classes of this program).
-Observer pattern: Works as a bridge between the two GUIs and the MasterControl, which are the observers and the observable subject respectively.
-Command pattern: To encapsulate in one object the information and instructions necessary to carry out a specific command, which in this case is executing or undoing the actions of turning an appliance on or off.<br>

Furthermore, a class _Appliance_ is implemented on each appliance for them to have the boolean attribute _isOn_, which points out their state and lets the program know how it should be portrayed in the GUIs.
