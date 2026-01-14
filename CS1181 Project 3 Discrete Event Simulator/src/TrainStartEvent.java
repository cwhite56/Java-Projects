/**
 * Custom class that represents the event when a train first blocks the crossing
 */
public class TrainStartEvent extends Event{

    public TrainStartEvent(int timeOccured, int name, int startTime) {
        super(timeOccured, name, startTime);
    }


    /**
     * Method that updates the current time and the train crossing flag
     */
    @Override
    public void execute() {
        Driver.setCurrentTime(this.getTimeOccured());
        Driver.setIsTrainCrossing(true);
    }
    
}
