/**
 * Custom class that represents the event when a train finishes the time in the crossing
 */
public class TrainStopEvent extends Event {

    public TrainStopEvent(int timeOccured, int name, int startTime) {
        super(timeOccured, name, startTime);
    }
    /**
     * Method that updates the current time and the train crossing flag
     */
    @Override
    public void execute() {
        Driver.setCurrentTime(this.getTimeOccured());
        Driver.setIsTrainCrossing(false);
    }
    
}
