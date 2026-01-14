/**
 * This class represents the 7 potential items to bring along
 */
public class Item {
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    public Item(Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
    }
    public double getWeight() {
        return weight;
    }
    public int getValue() {
        return value;
    }
    public boolean isIncluded() {
        return included;
    }
    public void setIncluded(boolean included) {
        this.included = included;
    }
    /**
     * toString method for Items
     * @return Item in the form: <name> (<weight> lbs, $<value>)
     */
    public String toString() {
        String toString = name + "(" + weight + " lbs, $" + value + ")";
        return toString;
        
    }
}
