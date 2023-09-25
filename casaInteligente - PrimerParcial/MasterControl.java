import Appliances.Appliance;
import Commands.Command;
import Commands.EmptyCommand;
import ObserverPattern.IObserver;
import ObserverPattern.ISubject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MasterControl implements ISubject {
    private Command[] OnCommands;
    private Command [] OffCommands;
    private SizedStack<Command> CommandsStack = new SizedStack<>(7);
    private Random random = new Random();

    private volatile boolean vacMode = false;

    public Boolean GetVacMode()
    {
        return vacMode;
    }
    Thread vacationThread = new Thread(this::VacationMode); // Create a new thread for VacacionesMode
    public int NumButtons = 0;

    public int getNumButtons() {
        return NumButtons;
    }

    private static MasterControl masterControl;
    public static MasterControl GetInstance()
    {
        if (masterControl == null)
        {
            return masterControl = new MasterControl();
        }
        else
        {
            return masterControl;
        }
    }

    ArrayList<Appliance> appliancesList = new ArrayList<>(); //arraylist to store the appliances
    //Observer pattern implementation
    private ArrayList<IObserver> IObserverList = new ArrayList<>();
    @Override
    public void registerObserver(IObserver IObserver) {
        IObserverList.add(IObserver);
    }

    @Override
    public void removeObserver(IObserver IObserver) {
        IObserverList.remove(IObserver);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < IObserverList.size(); i++){
            IObserverList.get(i).Update(appliancesList);
        }
    }

    public MasterControl() { 
        OnCommands = new Command[9];
        OffCommands = new Command[9];

        Command empty = new EmptyCommand();
        for(int i = 0; i <9; i++)
        {
            OnCommands[i] = empty;
            OffCommands[i] = empty;
        } 
    }

    public void setCommand(int commandNum, Command onCommand, Command offCommand){
        OnCommands[commandNum] = onCommand;
        OffCommands[commandNum] = offCommand;
        NumButtons++;
    }
    
    public void ButtonPressed_OFF (int ButtonNum) {
	    OffCommands[ButtonNum].execute();
        CommandsStack.push(OffCommands[ButtonNum]);
        notifyObserver();
	}

    public void ButtonPressed_ON (int ButtonNum) {
	    OnCommands[ButtonNum].execute();
        CommandsStack.push(OnCommands[ButtonNum]);
        notifyObserver();
	}

    @Override
    public String toString()
    {
        String Output = "";
        for(int i = 0; i< OnCommands.length; i++)
        {
            Output += "\n Commands "+ i +" is ON: " + OnCommands[i].toString()+" and OFF " +OffCommands[i].toString();
        }

        return Output;

    }

    public void UndoAll()
    {
        for ( int i = 0; !CommandsStack.isEmpty(); i++)
        {
            CommandsStack.pop().undo();
            notifyObserver();

        }
    }

    public void Undo_1()
    {
        CommandsStack.pop().undo();
        notifyObserver();
    }

    public void startVacationMode() {
        vacMode = true;
        vacationThread.start();

    }

    public void stopVacationMode() {
        vacMode = false; // Set the flag to "false" to stop the loop
        vacationThread.interrupt();
        vacationThread = new Thread(this::VacationMode);
    }
    int millis= 0;
    public void VacationMode()  {
        System.out.println("Vacation Mode Activated");
        int size = CommandsStack.size();
        Queue<Command> queue = new LinkedList<>();

        while(vacMode){
            for(int i = 0; i < size; i++)
            {

                if(vacMode)
                {
                    TimeDelay();
                }


                queue.offer(CommandsStack.peek());

                CommandsStack.pop().undo();
                notifyObserver();
            }

            for(int i = 0; i<size; i++)
            {

                if(vacMode)
                {
                    TimeDelay();
                }

                CommandsStack.push(queue.peek());
                queue.poll().execute();
                notifyObserver();
            }
        }

    }

    private void TimeDelay()
    {
        try {
            millis = random.nextInt(2001) + 1000;
            Thread.sleep(millis); // Sleep for 1 second
        } catch (InterruptedException e) {
            vacMode = false;

        }
    }




}
