public abstract class Event implements Comparable<Event>{

    private int timeOccured;
    private int name;
    private int startTime;

    public Event(int timeOccured, int name, int startTime) {
        this.timeOccured = timeOccured;
        this.name = name;
        this.startTime = startTime;
    }

    public abstract void execute();

    public int getTimeOccured() {
        return this.timeOccured;
    }
    public int getName() {
        return this.name;
    }
    public int getStartTime() {
        return this.startTime;
    }
    /**
     * Overidden compareTo method to compare the time occurances of various events
     */
    @Override
    public int compareTo(Event o) {
        return Integer.compare(this.timeOccured, o.timeOccured);
    }
    
}
