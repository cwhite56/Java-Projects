/**
 * Custom class that reprsents the event when a truck starts its route
 */
public class TruckStartEvent extends Event{

    public TruckStartEvent(int timeOccured, int name, int startTime) {
        super(timeOccured, name, startTime);
    }


    /**
     * Method that updates the current time 
     */
    @Override
    public void execute() {
        Driver.setCurrentTime(this.getTimeOccured());
    }
    
}
