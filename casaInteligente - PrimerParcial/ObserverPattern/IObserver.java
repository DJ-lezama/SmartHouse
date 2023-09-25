package ObserverPattern;

import Appliances.Appliance;

import java.util.ArrayList;

public interface IObserver {

    //Method to update the observer, used by the subject
     void Update(ArrayList<Appliance> appliances); // applianceList

}
