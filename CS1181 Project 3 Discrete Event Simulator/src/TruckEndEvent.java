/**
 * Custom class to represent the event that a truck finishes its route
 */
public class TruckEndEvent extends Event{

    public TruckEndEvent(int timeOccured, int name, int startTime) {
        super(timeOccured, name, startTime);
    }
    /**
     * Method that updates the current time and the train crossing flag
     */
    @Override
    public void execute() {
       Driver.setCurrentTime(this.getTimeOccured());
    }
}
