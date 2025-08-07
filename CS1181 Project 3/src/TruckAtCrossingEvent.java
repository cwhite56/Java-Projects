/**
 * Custom class to represent the event when a truck arrives at the railroad crossing
 */
public class TruckAtCrossingEvent extends Event{

    public TruckAtCrossingEvent(int timeOccured, int name, int startTime) {
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
