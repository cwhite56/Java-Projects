import java.util.*;

/**
 * This class represents the chromosome or sets of potential values for the item
 * picking problem
 */
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng = new Random();

    public Chromosome() {
    }

    public Chromosome(ArrayList<Item> items) {
        // Copy blueprint list into new chromosome and assign an included value
        /*I had to consult ChatGPT to see where I was going wrong. Initially, a new chromosome
         * would overwrite all previous chromosomes. That is because I was adding the same 7
         * items to the chromosome. Adding the "new Item" in the add line fixed this
        */
        for (int i = 0; i < items.size(); i++) {
            this.add(new Item(items.get(i)));
            int temp = rng.nextInt(2);
            if (temp == 0) {
                this.get(i).setIncluded(true);
            } else {
                this.get(i).setIncluded(false);
            }
        }
    }

    /**
     * Used to simulate reproduction of the chromosome objects
     * 
     * @param other the other chromosome to "bred" with this chromosome
     * @return the child chromosome
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();

        for (int i = 0; i < this.size(); i++) {
            int temp = rng.nextInt(2);

            // 50% chance for child to inherit either parents' item at i
            if (temp == 0) {
                child.add(new Item(this.get(i)));
            } else {
                child.add(new Item(other.get(i)));
            }
        }
        return child;
    }

    /**
     * Used to represent mutation in the wild by having a 10% chance to flip the
     * inclusion
     * of any given item
     */
    public void mutate() {

        for (int i = 0; i < this.size(); i++) {
            int temp = rng.nextInt(10) + 1;
            
            if (temp == 1) {
                this.get(i).setIncluded(!this.get(i).isIncluded());
            }

        }
    }

    /**
     * Used to find the value of a chromosome based on the described system
     * The value is equal to the monetary value unless weight > 10. Then fitness = 0
     * @return the value of the given chromosome
     */
    public int getFitness() {
        int fitness = 0;
        double weight = 0;
        // Add weight of all included items
        for (int i = 0; i < this.size(); i++) {

            if (this.get(i).isIncluded()) {
                weight += this.get(i).getWeight();
            }
        }

        if (weight > 10.0) {
            return 0;
            // If passable weight, add included items to value
        } else {

            for (int i = 0; i < this.size(); i++) {

                if (this.get(i).isIncluded()) {
                    fitness += this.get(i).getValue();
                }
            }
        }

        return fitness;
    }

    /**
     * Compares and sorts the set of chromosomes by fitness
     * 
     * @return the value to base sorting on
     */
    @Override
    public int compareTo(Chromosome other) {
        if (this.getFitness() > other.getFitness()) {
            return -1;
        } else if (this.getFitness() < other.getFitness()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * toString method for the given chromosome
     * 
     * @return list of included items with their associated weight and value
     *         along with the fitness of the chromosome
     */
    public String toString() {
        String toString = "Items included: ";
        // Add included items to the sum string
        for (int i = 0; i < this.size(); i++) {
            
            if (this.get(i).isIncluded()) {
                toString += this.get(i).toString() + ", ";
            }
        }
        toString += " Fitness: " + this.getFitness();

        return toString;
    }

}
