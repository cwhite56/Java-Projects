/**
 * Custom class to represent the event when a truck successfully crosses the railroad tracks
 */
public class TruckPassCrossingEvent extends Event{

    public TruckPassCrossingEvent(int timeOccured, int name, int startTime) {
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
